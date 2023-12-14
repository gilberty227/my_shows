package br.com.myshow.presenter.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import br.com.myshow.domain.model.Show
import br.com.myshow.domain.model.Ticket
import br.com.myshow.domain.repository.CartUseCase
import br.com.myshow.domain.repository.ShowUseCase
import br.com.myshow.domain.utils.getMoney
import br.com.myshow.presenter.model.CartUi
import br.com.myshow.presenter.model.ShowUi
import br.com.myshow.presenter.model.TicketUi
import br.com.myshow.presenter.model.toShowDto
import br.com.myshow.presenter.model.toTicket
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val cartUseCase: CartUseCase,
                                        private val showUseCase: ShowUseCase) : ViewModel() {

    private val _tickets = MutableLiveData<List<TicketUi>>()
    val tickets = _tickets as LiveData<List<TicketUi>>

    private val _updateCart = MutableLiveData<CartUi>()
    val updateCart = _updateCart as LiveData<CartUi>

    init {
        getTicket()
    }

    fun getTicket(updateAll: Boolean = true){
        viewModelScope.launch {
            val listTicket = cartUseCase.getAllTicketCart().toMutableList()
            val listIdShow = mutableListOf<Int>()
            val listTicketUi = mutableListOf<TicketUi>()
            listTicket.forEach { listIdShow.add((it.idShow?:0)) }
            val listShow = showUseCase.getShowsDatabase(listIdShow).toMutableList()
            listTicket.map { ticket ->
                listTicketUi.add(TicketUi(ticket.idShow, ticket.countTicket,
                    listShow.find { it.id == ticket.idShow }))
            }
            _updateCart.postValue(calculateCart(listTicket, listShow))
            if(updateAll) {
                _tickets.value = listTicketUi
            }
            updateCartMain() //Main
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

    private fun updateCartMain(){
        cartUseCase.updateCart()
    }

    fun removeTicket(ticketUi: TicketUi){
        viewModelScope.launch {
            cartUseCase.deleteTicketCart(ticketUi.toTicket())
            getTicket(false)
            updateCartMain()
        }
    }

    fun updateTicket(ticketUi: TicketUi){
        viewModelScope.launch {
            cartUseCase.updateTicketCart(ticketUi.toTicket())
            getTicket(false)
            updateCartMain()
        }
    }
}