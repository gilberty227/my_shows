package br.com.myshow.domain.model

data class Show(
    var id: Int?,
    var title: String?,
    var desc: String?,
    var imageUrl: String?,
    var price: Int?,
    var day: String?,
    var hour: String?,
    var location: String?,
    var locationQuick: String?
)
