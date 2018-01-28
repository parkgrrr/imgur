package net.parkerstevens.imgurdemo.features.detail

import net.parkerstevens.imugrclient.features.base.MvpView

interface DetailMvpView : MvpView {

    fun showProgress(show: Boolean)
}