package net.parkerstevens.imgurdemo.features.detail

import net.parkerstevens.imgurdemo.data.DataManager
import net.parkerstevens.imgurdemo.features.base.BasePresenter
import net.parkerstevens.imgurdemo.injection.ConfigPersistent
import javax.inject.Inject

@ConfigPersistent
class DetailPresenter @Inject
constructor(private val dataManager: DataManager) : BasePresenter<DetailMvpView>() {


}