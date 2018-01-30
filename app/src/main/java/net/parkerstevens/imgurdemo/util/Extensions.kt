package net.parkerstevens.imgurdemo.util

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import timber.log.Timber


fun ImageView.loadImageFromUrl(url: String, progressbar: ProgressBar? = null) {
    Glide.with(context)
            .load(url)
            .listener(object: RequestListener<Drawable?> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable?>?, isFirstResource: Boolean): Boolean {
                    Timber.e(e?.localizedMessage)
                    e?.printStackTrace()
                    return false                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable?>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    progressbar?.visibility = View.GONE
                    return false                }
            })
            .into(this)
}