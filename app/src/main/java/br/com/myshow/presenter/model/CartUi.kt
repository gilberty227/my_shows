package br.com.myshow.presenter.model

import android.os.Parcelable
import br.com.myshow.domain.model.Show
import kotlinx.parcelize.Parcelize

data class CartUi(
    var totalTicket: Int,
    var totalPrice: String
)

