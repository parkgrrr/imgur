package net.parkerstevens.imgurdemo.util

import android.widget.ImageView
import com.bumptech.glide.Glide


fun ImageView.loadImageFromUrl(url: String) {
    Glide.with(context)
            .load(url)
            .into(this)
}
