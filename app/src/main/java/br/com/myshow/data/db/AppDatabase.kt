package br.com.myshow.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.myshow.data.db.dao.TicketDao
import br.com.myshow.data.db.dao.OrderDao
import br.com.myshow.data.db.dao.ShowDao
import br.com.myshow.domain.model.Cart
import br.com.myshow.domain.model.Order
import br.com.myshow.domain.model.Show
import br.com.myshow.domain.model.Ticket

@Database(
    entities = [Show:: class, Order:: class, Ticket::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract val showDao: ShowDao
    abstract val orderDao: OrderDao
    abstract val ticketDao: TicketDao

    companion object {
        const val ORDER_NAME_TABLE = "order"
        const val DATABASE_NAME = "my_show_db"
        const val SHOW_NAME_TABLE = "show"
        const val TICKET_NAME_TABLE = "ticket"
    }
}