package br.com.myshow.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.myshow.domain.model.Show
import br.com.myshow.domain.model.Ticket
import br.com.myshow.domain.repository.CartUseCase
import br.com.myshow.domain.repository.ShowUseCase
import br.com.myshow.domain.utils.getMoney
import br.com.myshow.presenter.model.CartUi
import br.com.myshow.presenter.model.ShowUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private var showsUseCase: ShowUseCase,
                                        private var cartUseCase: CartUseCase) : ViewModel() {

    private val _updateCart = MutableLiveData<CartUi>()
    val updateCart = _updateCart as LiveData<CartUi>

    private val _updateShowCart = MutableLiveData<Boolean>()
    val updateShowCart = _updateShowCart as LiveData<Boolean>

    init {
        getCart()
        cartUseCase.observableDB().observeForever {
            getCart()
        }
    }

    private fun getCart(){
        viewModelScope.launch {
            val listTicket = cartUseCase.getAllTicketCart().toMutableList()
            _updateShowCart.value = listTicket.isNotEmpty()
            if(listTicket.isNotEmpty()){
                val listIdShow = mutableListOf<Int>()
                listTicket.forEach { listIdShow.add((it.idShow?:0)) }
                val listShow = showsUseCase.getShowsDatabase(listIdShow).toMutableList()
                _updateCart.postValue(calculateCart(listTicket, listShow))
            }
        }
    }

    private fun calculateCart(listTicket: MutableList<Ticket>, listShow: MutableList<Show>): CartUi {
        var totalPrice = 0
        var totalTicket = 0
        listTicket.forEach { ticket ->
            totalPrice += ((listShow.find { it.id == ticket.idShow }?.price?:0) * (ticket.countTicket?:0))
            totalTicket += (ticket.countTicket?:0)
        }
        return CartUi(totalTicket, totalPrice.getMoney())
    }
}