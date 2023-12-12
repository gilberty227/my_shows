package br.com.myshow.presenter.showdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.myshow.R
import br.com.myshow.presenter.model.ShowDto
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShowDetailsViewModel @Inject constructor() : ViewModel() {

    private val _show = MutableLiveData<ShowDto>()
    val show = _show as LiveData<ShowDto>

    private val _updatePrice = MutableLiveData<Int>()
    val updatePrice = _updatePrice as LiveData<Int>

    private val _message = MutableLiveData<Int>()
    val message = _message as LiveData<Int>

    private val _updateStatusButton = MutableLiveData<Pair<Boolean, Boolean>>()
    val updateStatusButton = _updateStatusButton as LiveData<Pair<Boolean, Boolean>>

    private var countTicket = 1

    fun setShow(show: ShowDto){
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

}