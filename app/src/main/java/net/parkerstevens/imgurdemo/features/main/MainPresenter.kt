package net.parkerstevens.imgurdemo.features.main

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import net.parkerstevens.imgurdemo.data.DataManager
import net.parkerstevens.imgurdemo.features.base.BasePresenter
import net.parkerstevens.imgurdemo.injection.ConfigPersistent
import javax.inject.Inject

@ConfigPersistent
class MainPresenter @Inject
constructor(private val dataManager: DataManager) : BasePresenter<MainMvpView>() {

    fun getImgurPage(search: String) {
        checkViewAttached()
        //mvpView?.showProgress(true)
        dataManager.getImgurPage(1, search)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ search ->
                    mvpView?.apply {
                        //showProgress(false)
                    }
                }) { throwable ->
                    mvpView?.apply {
                        //showProgress(false)
                    }
                }
    }
}