package net.parkerstevens.imgurdemo.features.main

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import net.parkerstevens.imgurdemo.R
import net.parkerstevens.imgurdemo.features.base.BaseActivity
import javax.inject.Inject


class MainActivity : BaseActivity(), MainMvpView, ImgurAdapter.ClickListener {

    @Inject lateinit var imgurAdapter: ImgurAdapter
    @Inject lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent().inject(this)
        mainPresenter.attachView(this)

        setSupportActionBar(main_toolbar)

        imgurAdapter.setClickListener(this)
        recyclerImgur?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = imgurAdapter
        }
        mainPresenter.getImgurPage("cats")
    }

    override fun layoutId() = R.layout.activity_main

    override fun onDestroy() {
        super.onDestroy()
        mainPresenter.detachView()
    }

    override fun showPage(search: String, page: Int) {

    }

    /*override fun showProgress(show: Boolean) {
        if (show) {
            if (recyclerImgur?.visibility == View.VISIBLE && imgurAdapter.itemCount > 0) {
            } else {
            }

        } else {
            swipeToRefresh?.isRefreshing = false
        }
    }*/

    override fun onImageClick(img: String) {
        //startActivity(DetailActivity.getStartIntent(this, img))
    }
}