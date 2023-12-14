package br.com.myshow.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.myshow.data.db.AppDatabase
import br.com.myshow.data.db.dao.TicketDao
import br.com.myshow.domain.model.Ticket
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TicketRepositoryImp @Inject constructor(private val database: AppDatabase): TicketRepository {

    private val _observableDB = MutableLiveData<Boolean>()
    val observableDB = _observableDB as LiveData<Boolean>

    override suspend fun insertTicketCart(ticket: Ticket) {
        database.ticketDao.insertTicketCart(ticket)
    }

    override suspend fun updateTicketCart(ticket: Ticket) {
        database.ticketDao.updateTicketCart(ticket)
    }

    override suspend fun clearCart() {
        database.ticketDao.clearCart()
    }

    override suspend fun deleteTicketCart(ticket: Ticket) {
        database.ticketDao.deleteTicketCart(ticket)
    }

    override suspend fun getAllTicketCart(): List<Ticket> {
        return database.ticketDao.getAllTicketCart()
    }

    override fun observableDB(): LiveData<Boolean> {
        return observableDB
    }

    override fun updateCart() {
        _observableDB.value = true
    }
}

interface TicketRepository {

    suspend fun insertTicketCart(ticket: Ticket)

    suspend fun updateTicketCart(ticket: Ticket)

    suspend fun clearCart()

    suspend fun deleteTicketCart(ticket: Ticket)

    suspend fun getAllTicketCart(): List<Ticket>

    fun observableDB(): LiveData<Boolean>
    fun updateCart()
}