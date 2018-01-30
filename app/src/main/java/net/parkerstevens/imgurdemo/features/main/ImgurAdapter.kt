package net.parkerstevens.imgurdemo.features.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.item_image.view.*
import net.parkerstevens.imgurdemo.R
import net.parkerstevens.imgurdemo.data.model.ImgurImage
import net.parkerstevens.imgurdemo.util.loadImageFromUrl
import javax.inject.Inject

class ImgurAdapter @Inject
constructor() : RecyclerView.Adapter<ImgurAdapter.ImgurViewHolder>() {

    var imagesList: List<ImgurImage>
    var clickListener: ClickListener? = null

    init {
        imagesList = emptyList<ImgurImage>()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImgurViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_image, parent, false)
        return ImgurViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImgurViewHolder, position: Int) {
        val imgurImage = imagesList[position]
        holder.bindImage(imgurImage)
    }

    override fun getItemCount(): Int {
        return imagesList.size
    }

    interface ClickListener {
        fun onImageClick(url: String, title: String)
    }

    inner class ImgurViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var selectedImage: ImgurImage

        @BindView(R.id.image_preview)
        @JvmField var imgurPreview: ImageView? = null

        @BindView(R.id.image_title)
        @JvmField var imgurTitle: TextView? = null

        init {
            ButterKnife.bind(this, itemView)
            itemView.setOnClickListener {
                clickListener?.onImageClick(selectedImage.link, selectedImage.title ?: " ")
            }
        }

        fun bindImage(imgurImage: ImgurImage) {
            //itemView.image_preview
            selectedImage = imgurImage
            imgurPreview?.bottom = 0
            itemView.preview_progress.visibility = View.VISIBLE
            imgurPreview?.loadImageFromUrl(imgurImage.link, itemView.preview_progress)
            imgurTitle?.setText(imgurImage.title)
        }
    }
}