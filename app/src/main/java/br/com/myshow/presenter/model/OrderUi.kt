package br.com.myshow.presenter.model

import br.com.myshow.domain.model.Order


data class OrderUi(
    var showTicket: MutableMap<Int, String>,
    var totalPrice: String?,
    var countTickets: Int?,
    var dateBuy: Long?
)

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
