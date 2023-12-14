package br.com.myshow.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import br.com.myshow.data.db.AppDatabase
import br.com.myshow.domain.model.Ticket

@Dao
interface TicketDao {

    @Query("SELECT * FROM ${AppDatabase.TICKET_NAME_TABLE}")
    suspend fun getAllTicketCart(): List<Ticket>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTicketCart(ticket: Ticket)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTicketCart(ticket: Ticket)

    @Delete
    suspend fun deleteTicketCart(ticket: Ticket)

    @Query("DELETE FROM ${AppDatabase.TICKET_NAME_TABLE}")
    suspend fun clearCart()
}