package br.com.myshow.presenter.model

import android.os.Parcelable
import br.com.myshow.domain.model.Order
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrderUi(
    var showTicket: MutableMap<Int, Int>,
    var totalPrice: String?,
    var countTickets: Int?,
    var dateBuy: Long?
): Parcelable

fun OrderUi.toOrder(): Order {
    var showAndTicket = ""
    this.showTicket.forEach { idShow, countTickect ->
        showAndTicket = showAndTicket + "" + idShow + "&" + countTickect + "#"
    }

    return Order(
        showTicket = showAndTicket,
        totalPrice = this.totalPrice,
        countTickets = this.countTickets,
        dateBuy = this.dateBuy
    )
}
