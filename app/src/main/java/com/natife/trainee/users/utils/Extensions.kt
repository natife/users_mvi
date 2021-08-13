package com.natife.trainee.users.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.natife.trainee.users.R

fun ImageView.loadCircle(url: String) {
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.profile_placeholder)
        .circleCrop()
        .into(this)
}

fun ImageView.loadImage(url: String) {
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.profile_placeholder)
        .into(this)
}