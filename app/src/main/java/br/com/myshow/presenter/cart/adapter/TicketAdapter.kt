package br.com.myshow.presenter.cart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.myshow.databinding.RowTicketBinding
import br.com.myshow.domain.model.Ticket
import br.com.myshow.presenter.model.TicketUi

class TicketAdapter(
    private var listTicket: MutableList<TicketUi>,
    private var listener: TicketListener,
    private var clickFinish: (ticket: TicketUi) -> Unit)
    : RecyclerView.Adapter<TicketItemViewHolder>() {

    override fun getItemCount(): Int {
        return listTicket.count()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketItemViewHolder {
        return TicketItemViewHolder(RowTicketBinding.inflate(LayoutInflater.from(parent.context),
            parent,false)
        )
    }

    override fun onBindViewHolder(holder: TicketItemViewHolder, position: Int) {
        holder.setData(listTicket[holder.adapterPosition], listener, clickFinish)
    }

    fun removeItem(position: Int) {
        listTicket.removeAt(position)
        notifyItemRemoved(position)
    }
}