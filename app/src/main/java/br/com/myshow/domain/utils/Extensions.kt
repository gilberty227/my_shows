package br.com.myshow.domain.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import jp.wasabeef.glide.transformations.BlurTransformation
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


fun ImageView.loadImage(url: String) {
    Glide.with(this.context)
        .load(url)
        .into(this)
}

fun ImageView.loadImageBlur(url: String) {
    Glide.with(this.context).load(url).apply(RequestOptions.bitmapTransform(BlurTransformation()))
        .into(this)
}

fun Int.getMoney(): String {
    return ("R$ $this,00")
}

fun Long.convertMilliSecondsToDate(): String {
    val date = Date(this)
    val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return format.format(date)
}