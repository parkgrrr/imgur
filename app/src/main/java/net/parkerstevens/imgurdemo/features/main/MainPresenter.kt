package net.parkerstevens.imgurdemo.features.main

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import net.parkerstevens.imgurdemo.data.DataManager
import net.parkerstevens.imgurdemo.features.base.BasePresenter
import net.parkerstevens.imgurdemo.injection.ConfigPersistent
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@ConfigPersistent
class MainPresenter @Inject
constructor(private val dataManager: DataManager) : BasePresenter<MainMvpView>() {

    fun getImgurPage(search: String, page: Int = 1) {
        checkViewAttached()
        val disposable = dataManager.getImgurPage(page, search)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { mvpView?.showProgress(true) }
                .doFinally {mvpView?.showProgress(false)}
                .subscribe({ response ->
                    mvpView?.apply {
                        showPage(response, page)
                        Timber.i("good response")
                    }
                }) { throwable ->
                    mvpView?.apply {
                        Timber.e(throwable.localizedMessage)
                        showError(throwable.localizedMessage)
                    }
                }
        addDisposable(disposable)
    }

    fun createTextChangeObservable(): Observable<String> {
        checkViewAttached()
        val textChangeObservable = Observable.create<String> { emitter ->
            mvpView?.listenText(emitter)
        }

        textChangeObservable
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .filter { it.length > 2 }
                .debounce(250, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { query ->
                    getImgurPage(query)
                }
        return textChangeObservable
    }
}