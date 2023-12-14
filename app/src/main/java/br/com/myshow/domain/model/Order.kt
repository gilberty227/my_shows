package br.com.myshow.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.myshow.data.db.AppDatabase
import br.com.myshow.presenter.model.OrderUi

@Entity(tableName = AppDatabase.ORDER_NAME_TABLE)
data class Order(
    var showTicket: String?,
    var totalPrice: String?,
    var countTickets: Int?,
    @PrimaryKey var dateBuy: Long?
)

fun Order.toOrderUi(): OrderUi {
    val showAndTicket: MutableMap<Int, String> = mutableMapOf()
    val listResult = this.showTicket?.split("#")
    listResult?.forEach {
        val result = it.split("&")
        val showId = if(result.first().isEmpty()) "0" else result.first()

        showAndTicket[showId.toInt()] = result.last()
    }

    return OrderUi(
        showTicket = showAndTicket,
        totalPrice = this.totalPrice,
        countTickets = this.countTickets,
        dateBuy = this.dateBuy)
}