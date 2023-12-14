package br.com.myshow.domain.repository

import androidx.lifecycle.LiveData
import br.com.myshow.data.repository.TicketRepository
import br.com.myshow.domain.model.Ticket
import javax.inject.Inject


class GetTicketCart @Inject constructor(private val ticketRepository: TicketRepository) : CartUseCase {

    override suspend fun insertTicketCart(ticket: Ticket) {
        ticketRepository.insertTicketCart(ticket)
    }

    override suspend fun updateTicketCart(ticket: Ticket) {
        ticketRepository.updateTicketCart(ticket)
    }

    override suspend fun clearCart() {
        ticketRepository.clearCart()
    }

    override suspend fun deleteTicketCart(ticket: Ticket) {
        ticketRepository.deleteTicketCart(ticket)
    }

    override suspend fun getAllTicketCart(): List<Ticket> {
        return ticketRepository.getAllTicketCart()
    }

    override fun observableDB(): LiveData<Boolean> {
        return ticketRepository.observableDB()
    }

    override fun updateCart() {
        ticketRepository.updateCart()
    }
}

interface  CartUseCase {
    suspend fun insertTicketCart(ticket: Ticket)

    suspend fun updateTicketCart(ticket: Ticket)

    suspend fun clearCart()

    suspend fun deleteTicketCart(ticket: Ticket)

    suspend fun getAllTicketCart(): List<Ticket>

    fun observableDB(): LiveData<Boolean>

    fun updateCart()
}