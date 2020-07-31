package com.example.assessmentspectrumglobal

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

/**
Created by kiranb on 31/7/20
 */

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(imageUrl: String?) {
    if (imageUrl != null) {
        val uri = imageUrl.replace("http", "https")
        Picasso.get()
            .load(uri)
            .placeholder(R.drawable.wrapped_ic_company)
            .into(this)
    }
}