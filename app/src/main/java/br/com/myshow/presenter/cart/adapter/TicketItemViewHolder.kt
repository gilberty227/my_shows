package br.com.myshow.presenter.cart.adapter

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.myshow.R
import br.com.myshow.databinding.RowTicketBinding
import br.com.myshow.domain.model.Ticket
import br.com.myshow.domain.utils.getMoney
import br.com.myshow.domain.utils.loadImage
import br.com.myshow.presenter.model.TicketUi

class TicketItemViewHolder(private val binding: RowTicketBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun setData(ticket: TicketUi, listener: TicketListener) {

        binding.imageViewShow.loadImage(ticket.show?.imageUrl.orEmpty())
        binding.textViewShowTitle.text = ticket.show?.title
        binding.textViewPriceUnit.text = ticket.show?.price?.getMoney()
        binding.textViewPrice.text = ((ticket.show?.price?:0) * (ticket.countTicket?:0)).getMoney()
        binding.textViewNumber.text = ticket.countTicket.toString()

        binding.imageViewMinus.setOnClickListener {
            val countTicket = (ticket.countTicket?:0) - 1
            if((ticket.countTicket?:0) <= 1){
                ticket.countTicket = countTicket
                listener.removeTicket(ticket, adapterPosition)
            } else {
                binding.textViewNumber.text = countTicket.toString()
                ticket.countTicket = countTicket
                listener.updateCart(ticket)
            }

            if((ticket.countTicket?:0) <= 7){
                updateButtonMore(true)
            }
        }

        binding.imageViewMore.setOnClickListener {
            val countTicket = (ticket.countTicket?:0) + 1
            if((ticket.countTicket?:0) >= 8){
                listener.maxTicket()
                updateButtonMore(false)
            } else {
                ticket.countTicket = countTicket
                binding.textViewNumber.text = countTicket.toString()
                listener.updateCart(ticket)
            }
        }
    }

    fun updateButtonMore(activate: Boolean){
        binding.imageViewMore.isEnabled = activate
        binding.imageViewMore.drawable.setTint(
            ContextCompat.getColor(itemView.context,
            if(activate) R.color.colorTextBuy else R.color.colorTextTitle))
    }
}