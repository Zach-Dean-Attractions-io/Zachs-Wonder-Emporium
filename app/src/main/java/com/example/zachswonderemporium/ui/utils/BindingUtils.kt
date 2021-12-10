package com.example.zachswonderemporium.ui.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.zachswonderemporium.R

@BindingAdapter("imageUrl")
fun ImageView.setItemImage(imageUrl: String) {
    if(imageUrl.isNotEmpty()) {
        Glide.with(this.context)
            .load(imageUrl)
            .placeholder(R.drawable.amusement_park)
            .error(R.drawable.broken_image)
            .fitCenter()
            .into(this)
    }
}