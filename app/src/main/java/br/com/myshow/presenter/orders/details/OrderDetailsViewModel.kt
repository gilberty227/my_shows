package br.com.myshow.presenter.orders.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.myshow.domain.model.Show
import br.com.myshow.domain.repository.CartUseCase
import br.com.myshow.domain.repository.OrderUseCase
import br.com.myshow.domain.repository.ShowUseCase
import br.com.myshow.domain.utils.getMoney
import br.com.myshow.presenter.model.CartUi
import br.com.myshow.presenter.model.OrderUi
import br.com.myshow.presenter.model.TicketUi
import br.com.myshow.presenter.model.toOrder
import br.com.myshow.presenter.model.toTicket
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderDetailsViewModel @Inject constructor(private val cartUseCase: CartUseCase,
                                                private val showUseCase: ShowUseCase,
                                                private val orderUseCase: OrderUseCase) : ViewModel() {



    private val _order = MutableLiveData<OrderUi>()
    val order = _order as LiveData<OrderUi>

    private val _tickets = MutableLiveData<List<TicketUi>>()
    val tickets = _tickets as LiveData<List<TicketUi>>

    private val _updateCart = MutableLiveData<CartUi>()
    val updateCart = _updateCart as LiveData<CartUi>

    fun setOrder(order: OrderUi){
        _order.value = order
        getTicket(order)
    }

    private fun getTicket(order: OrderUi){
        viewModelScope.launch {
            val listIdShow = mutableListOf<Int>()
            order.showTicket.forEach { key, value ->
                listIdShow.add(key)
            }
            val listTicketUi = mutableListOf<TicketUi>()
            val listShow = showUseCase.getShowsDatabase(listIdShow).toMutableList()
            order.showTicket.forEach { key, value ->
                listTicketUi.add(TicketUi(key, value, listShow.find { it.id == key }))
            }
            _updateCart.postValue(calculateCart(listTicketUi))
            _tickets.value = listTicketUi
        }
    }

    private fun calculateCart(listTicket: MutableList<TicketUi>): CartUi {
        var totalPrice = 0
        var totalTicket = 0
        listTicket.forEach { ticket ->
            totalPrice += ((ticket.show?.price?:0) * (ticket.countTicket?:0))
            totalTicket += (ticket.countTicket?:0)
        }
        return CartUi(totalTicket, totalPrice.getMoney())
    }

}