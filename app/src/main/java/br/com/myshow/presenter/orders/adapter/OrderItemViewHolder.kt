package br.com.myshow.presenter.orders.adapter

import androidx.recyclerview.widget.RecyclerView
import br.com.myshow.R
import br.com.myshow.databinding.RowOrderBinding
import br.com.myshow.domain.utils.convertMilliSecondsToDate
import br.com.myshow.presenter.model.OrderUi
import br.com.myshow.presenter.model.ShowUi

class OrderItemViewHolder(private val binding: RowOrderBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun setData(order: OrderUi, listener: (order: OrderUi) -> Unit) {
        binding.textViewNumberOrder.text = itemView.context.resources
            .getQuantityString(R.plurals.cart_ticket,
                order.countTickets?:0, order.countTickets?:0)
        binding.textViewPrice.text = order.totalPrice
        binding.textViewDateOrder.text = (order.dateBuy?.toLong())?.convertMilliSecondsToDate()
    }
}