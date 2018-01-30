package net.parkerstevens.imgurdemo.features.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.item_image.*
import net.parkerstevens.imgurdemo.R
import net.parkerstevens.imgurdemo.features.base.BaseActivity
import net.parkerstevens.imgurdemo.util.loadImageFromUrl
import javax.inject.Inject



class DetailActivity : BaseActivity(), DetailMvpView {

    @Inject lateinit var detailPresenter: DetailPresenter

    private var imageUrl: String? = null
    private var imageTitle: String? = null

    companion object {
        val EXTRA_IMAGE_URL = "extraImageUrl"
        val EXTRA_IMAGE_TITLE = "extraImageTitle"

        fun getStartIntent(context: Context, imageUrl: String, title: String): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_IMAGE_URL, imageUrl)
            intent.putExtra(EXTRA_IMAGE_TITLE, title)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        activityComponent().inject(this)
        detailPresenter.attachView(this)

        imageUrl = intent.getStringExtra(EXTRA_IMAGE_URL)
        imageTitle = intent.getStringExtra(EXTRA_IMAGE_TITLE)

        image_big.loadImageFromUrl(imageUrl?: " ", preview_progress)
        title_big.text = imageTitle ?: ""

        imageUrl?: throw IllegalArgumentException("Detail Activity requires a url")

        setSupportActionBar(detail_toolbar)
        val actionBar = supportActionBar
        actionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun layoutId() = R.layout.activity_detail

    override fun showProgress(show: Boolean) {
       // progress?.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        detailPresenter.detachView()
    }
}