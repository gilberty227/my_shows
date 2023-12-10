package br.com.myshow.presenter.model

import br.com.myshow.domain.model.Show

class ShowDto(
    var id: Int?,
    var title: String?,
    var desc: String?,
    var imageUrl: String?,
    var price: Int?,
    var day: String?,
    var hour: String?,
    var location: String?
)

fun Show.toShowDto() = ShowDto(
    id = this.id,
    title = this.title,
    desc = this.desc,
    imageUrl = this.imageUrl,
    price = this.price,
    day = this.day,
    hour = this.hour,
    location = this.location
)