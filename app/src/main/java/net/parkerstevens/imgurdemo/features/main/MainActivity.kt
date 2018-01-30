package net.parkerstevens.imgurdemo.features.main

import android.os.Bundle
import android.os.Parcelable
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import io.reactivex.ObservableEmitter
import kotlinx.android.synthetic.main.activity_main.*
import net.parkerstevens.imgurdemo.R
import net.parkerstevens.imgurdemo.data.model.ImgurImage
import net.parkerstevens.imgurdemo.features.base.BaseActivity
import net.parkerstevens.imgurdemo.features.common.EndlessRecyclerViewScrollListener
import net.parkerstevens.imgurdemo.features.detail.DetailActivity
import java.util.*
import javax.inject.Inject


class MainActivity : BaseActivity(), MainMvpView, ImgurAdapter.ClickListener {

    @Inject lateinit var imgurAdapter: ImgurAdapter
    @Inject lateinit var mainPresenter: MainPresenter
    lateinit var scrollListener: EndlessRecyclerViewScrollListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent().inject(this)
        mainPresenter.attachView(this)

        setSupportActionBar(main_toolbar)

        imgurAdapter.clickListener = this

        recycler_imgur?.apply {
            val newLayoutManager = LinearLayoutManager(context)
            layoutManager = newLayoutManager
            adapter = imgurAdapter
            scrollListener = object: EndlessRecyclerViewScrollListener(layoutManager = newLayoutManager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                    if (page > 1) mainPresenter.getImgurPage(search_edit.text.toString(), page)
                }
            }
            addOnScrollListener(scrollListener)
        }

        var imgurList: List<ImgurImage>? = null
        savedInstanceState?.apply {
            imgurList = getParcelableArrayList("test")
        }

        imgurList?.apply {
            imgurAdapter.imagesList = this
            imgurAdapter.notifyDataSetChanged()
        }

        val textChangeObservable = mainPresenter.createTextChangeObservable()
    }



    override fun layoutId() = R.layout.activity_main

    override fun onDestroy() {
        super.onDestroy()
        mainPresenter.detachView()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        imgurAdapter.imagesList.apply {
            outState.putParcelableArrayList("test", this as ArrayList<out Parcelable>)
        }
    }

    override fun showPage(imgurs: List<ImgurImage>, page : Int) {
        imgurAdapter.apply {
            if (page == 1) clearRecycler()
            imagesList = imagesList.plus(imgurs)
            notifyItemRangeInserted(imagesList.size - (imgurs.size), imgurs.size)
            //notifyDataSetChanged()
        }
    }

    override fun listenText(emitter: ObservableEmitter<String>) {
        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = Unit
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
            override fun onTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                s?.toString()?.let { emitter.onNext(it) }
            }
        }
        search_edit.addTextChangedListener(textWatcher)
        emitter.setCancellable {
            search_edit.removeTextChangedListener(textWatcher)
        }
    }

    override fun clearRecycler() {
        imgurAdapter.imagesList = emptyList()
        imgurAdapter.notifyDataSetChanged()
        scrollListener.resetState()
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            progress_bar?.visibility = View.VISIBLE
        } else {
            progress_bar?.visibility = View.INVISIBLE
        }
    }

    override fun onImageClick(url: String, title: String) {
        startActivity(DetailActivity.getStartIntent(this, url, title))
    }
}