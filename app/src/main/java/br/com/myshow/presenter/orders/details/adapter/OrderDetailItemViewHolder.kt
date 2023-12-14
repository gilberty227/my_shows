package br.com.myshow.presenter.orders.details.adapter

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.myshow.R
import br.com.myshow.databinding.RowTicketBinding
import br.com.myshow.databinding.RowTicketOrderDetailsBinding
import br.com.myshow.domain.model.Ticket
import br.com.myshow.domain.utils.getMoney
import br.com.myshow.domain.utils.loadImage
import br.com.myshow.presenter.model.TicketUi

class OrderDetailItemViewHolder(private val binding: RowTicketOrderDetailsBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun setData(ticket: TicketUi) {
        binding.imageViewShow.loadImage(ticket.show?.imageUrl.orEmpty())
        binding.textViewShowTitle.text = ticket.show?.title
        binding.textViewPriceUnit.text = ticket.show?.price?.getMoney()
        binding.textViewPrice.text = ((ticket.show?.price?:0) * (ticket.countTicket?:0)).getMoney()
        binding.textViewNumber.text = ticket.countTicket.toString()
    }
}