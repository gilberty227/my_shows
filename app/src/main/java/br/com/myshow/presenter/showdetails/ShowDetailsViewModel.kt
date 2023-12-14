package br.com.myshow.presenter.showdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.myshow.R
import br.com.myshow.domain.model.Show
import br.com.myshow.domain.model.Ticket
import br.com.myshow.domain.repository.CartUseCase
import br.com.myshow.domain.repository.ShowUseCase
import br.com.myshow.presenter.model.ShowUi
import br.com.myshow.presenter.model.toShow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowDetailsViewModel @Inject constructor(private val cartUseCase: CartUseCase,
                                               private val showUseCase: ShowUseCase) : ViewModel() {


    private val _show = MutableLiveData<ShowUi>()
    val show = _show as LiveData<ShowUi>

    private val _updatePrice = MutableLiveData<Int>()
    val updatePrice = _updatePrice as LiveData<Int>

    private val _message = MutableLiveData<Int>()
    val message = _message as LiveData<Int>

    private val _updateStatusButton = MutableLiveData<Pair<Boolean, Boolean>>()
    val updateStatusButton = _updateStatusButton as LiveData<Pair<Boolean, Boolean>>

    private var countTicket = 1

    fun setShow(show: ShowUi){
        _show.value = show
        updateButtons(countTicket)
    }

    fun changeNumberItem(count: Int){
        countTicket = count
        _updatePrice.value = (count * (_show.value?.price?:0))
        updateButtons(count)
    }

    private fun updateButtons(count: Int) {
        //Pair(true, false) First minus Second more
        if (count <= 1){
            _updateStatusButton.value = Pair(false, true)
        } else if (count >= 8) {
            _updateStatusButton.value = Pair(true, false)
            _message.value = R.string.max_items
        } else {
            _updateStatusButton.value = Pair(true, true)
        }
    }

    fun saveTicket(countTicket: Int) {
        _show.value?.let { insertShow(it.toShow()) }
        _show.value?.id?.let { insertTicket(it, countTicket) }
        cartUseCase.updateCart()
    }

    private fun insertTicket(idShow: Int, countTicket: Int){
        viewModelScope.launch {
            cartUseCase.insertTicketCart(Ticket(idShow, countTicket))
        }
    }

    private fun insertShow(show: Show) {
        viewModelScope.launch {
            showUseCase.insertShow(show)
        }
    }

}