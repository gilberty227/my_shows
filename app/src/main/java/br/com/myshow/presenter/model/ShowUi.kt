package br.com.myshow.presenter.model

import android.os.Parcelable
import br.com.myshow.domain.model.Show
import kotlinx.parcelize.Parcelize

@Parcelize
data class ShowUi(
    var id: Int?,
    var title: String?,
    var desc: String?,
    var imageUrl: String?,
    var price: Int?,
    var day: String?,
    var hour: String?,
    var location: String?,
    var locationQuick: String?
) : Parcelable

fun Show.toShowDto() = ShowUi(
    id = this.id,
    title = this.title,
    desc = this.desc,
    imageUrl = this.imageUrl,
    price = this.price,
    day = this.day,
    hour = this.hour,
    location = this.location,
    locationQuick = this.locationQuick
)

fun ShowUi.toShow() = Show(
    id = this.id,
    title = this.title,
    desc = this.desc,
    imageUrl = this.imageUrl,
    price = this.price,
    day = this.day,
    hour = this.hour,
    location = this.location,
    locationQuick = this.locationQuick
)