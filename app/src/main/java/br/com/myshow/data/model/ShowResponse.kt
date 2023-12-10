package br.com.myshow.data.model

import br.com.myshow.domain.model.Show
import com.google.gson.annotations.SerializedName

data class ShowResponse(
    @SerializedName("id") var id: Int?,
    @SerializedName("title") var title: String?,
    @SerializedName("desc") var desc: String?,
    @SerializedName("image_url") var imageUrl: String?,
    @SerializedName("price") var price: Int?,
    @SerializedName("day") var day: String?,
    @SerializedName("hour") var hour: String?,
    @SerializedName("location")var location: String?
)

fun ShowResponse.toShow() = Show(
    id = this.id,
    title = this.title,
    desc = this.desc,
    imageUrl = this.imageUrl,
    price = this.price,
    day = this.day,
    hour = this.hour,
    location = this.location
)
