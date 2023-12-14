package br.com.myshow.presenter.cart.adapter

import br.com.myshow.presenter.model.TicketUi

interface TicketListener {
    fun removeTicket(ticket: TicketUi, position: Int)
    fun updateCart(ticket: TicketUi)
    fun maxTicket()
}