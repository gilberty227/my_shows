package br.com.myshow.presenter.orders.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.myshow.databinding.RowTicketBinding
import br.com.myshow.databinding.RowTicketOrderDetailsBinding
import br.com.myshow.domain.model.Ticket
import br.com.myshow.presenter.model.TicketUi

class OrderDetailsAdapter(
    private var listTicket: MutableList<TicketUi>)
    : RecyclerView.Adapter<OrderDetailItemViewHolder>() {

    override fun getItemCount(): Int {
        return listTicket.count()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderDetailItemViewHolder {
        return OrderDetailItemViewHolder(RowTicketOrderDetailsBinding.inflate(LayoutInflater.from(parent.context),
            parent,false)
        )
    }

    override fun onBindViewHolder(holder: OrderDetailItemViewHolder, position: Int) {
        holder.setData(listTicket[holder.adapterPosition])
    }
}