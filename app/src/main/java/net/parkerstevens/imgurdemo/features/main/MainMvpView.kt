package net.parkerstevens.imgurdemo.features.main

import io.reactivex.ObservableEmitter
import net.parkerstevens.imgurdemo.data.model.ImgurImage
import net.parkerstevens.imugrclient.features.base.MvpView

interface MainMvpView : MvpView {

    fun showProgress(show: Boolean)

    fun showPage(imgurs: List<ImgurImage>, page: Int)

    fun listenText(emitter: ObservableEmitter<String>)

    fun clearRecycler()

    fun showError(message: String)
}