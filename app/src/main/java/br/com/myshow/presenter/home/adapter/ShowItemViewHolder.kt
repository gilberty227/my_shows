package br.com.myshow.presenter.home.adapter

import androidx.recyclerview.widget.RecyclerView
import br.com.myshow.databinding.RowShowBinding
import br.com.myshow.presenter.model.ShowUi
import br.com.myshow.domain.utils.loadImage

class ShowItemViewHolder(private val binding: RowShowBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun setData(show: ShowUi, listener: (show: ShowUi) -> Unit) {

        binding.imageViewShow.loadImage(show.imageUrl.orEmpty())
        binding.textViewShowTitle.text = show.title
        binding.textViewShowLocale.text = show.locationQuick
        binding.textViewShowDate.text = show.day?.replace(" de 2023", "")?.replace(" de 2024", "")

        itemView.setOnClickListener {
            listener(show)
        }
    }
}