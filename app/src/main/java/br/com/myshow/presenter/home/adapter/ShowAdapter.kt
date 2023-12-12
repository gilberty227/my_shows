package br.com.myshow.presenter.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.myshow.databinding.RowShowBinding
import br.com.myshow.presenter.model.ShowDto

class ShowAdapter(
    private var listShow: MutableList<ShowDto>,
    private var listener: (show: ShowDto) -> Unit)
    : RecyclerView.Adapter<ShowItemViewHolder>() {

    override fun getItemCount(): Int {
        return listShow.count()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowItemViewHolder {
        return ShowItemViewHolder(RowShowBinding.inflate(LayoutInflater.from(parent.context),
            parent,false)
        )
    }

    override fun onBindViewHolder(holder: ShowItemViewHolder, position: Int) {
        holder.setData(listShow[holder.adapterPosition], listener)
    }
}