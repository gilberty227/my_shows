package br.com.myshow.presenter.orders.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.myshow.databinding.RowOrderBinding
import br.com.myshow.presenter.model.OrderUi
import br.com.myshow.presenter.model.ShowUi

class OrderAdapter(
    private var listOrder: MutableList<OrderUi>,
    private var listener: (order: OrderUi) -> Unit)
    : RecyclerView.Adapter<OrderItemViewHolder>() {

    override fun getItemCount(): Int {
        return listOrder.count()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderItemViewHolder {
        return OrderItemViewHolder(
            RowOrderBinding.inflate(LayoutInflater.from(parent.context),
            parent,false)
        )
    }

    override fun onBindViewHolder(holder: OrderItemViewHolder, position: Int) {
        holder.setData(listOrder[holder.adapterPosition], listener)
    }
}