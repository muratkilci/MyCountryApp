package com.example.mycountryapp.util

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mycountryapp.R


fun ImageView.downloadFromUrl(url: String?, progressDrawable: CircularProgressDrawable) {

    val option = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher_round)

    Glide.with(context)
        .setDefaultRequestOptions(option)
        .load(url)
        .into(this)

}

fun placeholderProgressBar(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 5f
        centerRadius = 30f
        start()
    }
}

@BindingAdapter("android:downloadUrl")                                    //Xml içinde çalıştırmak için
fun downloadImage(view: ImageView,url: String?){
    view.downloadFromUrl(url, placeholderProgressBar(view.context))
}