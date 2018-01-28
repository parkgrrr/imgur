package net.parkerstevens.imgurdemo.features.main

import net.parkerstevens.imugrclient.features.base.MvpView

interface MainMvpView : MvpView {

    //fun showProgress(show: Boolean)

    fun showPage(search: String, page: Int)

}