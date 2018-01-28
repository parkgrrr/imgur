package net.parkerstevens.imgurdemo.features.detail

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import net.parkerstevens.imgurdemo.data.DataManager
import net.parkerstevens.imgurdemo.features.base.BasePresenter
import net.parkerstevens.imgurdemo.injection.ConfigPersistent
import javax.inject.Inject

@ConfigPersistent
class DetailPresenter @Inject
constructor(private val dataManager: DataManager) : BasePresenter<DetailMvpView>() {

    fun getImgurPage(search: String) {
        checkViewAttached()
        mvpView?.showProgress(true)
        dataManager.getImgurPage(1,search)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ results ->
                    // It should be always checked if MvpView (Fragment or Activity) is attached.
                    // Calling showProgress() on a not-attached fragment will throw a NPE
                    // It is possible to ask isAdded() in the fragment, but it's better to ask in the presenter
                    mvpView?.apply {
                        showProgress(false)
                    }
                }) { throwable ->
                    mvpView?.apply {
                        showProgress(false)
                    }
                }
    }
}