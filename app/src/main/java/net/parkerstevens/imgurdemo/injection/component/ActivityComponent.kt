package net.parkerstevens.imgurdemo.injection.component

import dagger.Subcomponent
import net.parkerstevens.imgurdemo.features.base.BaseActivity
import net.parkerstevens.imgurdemo.features.detail.DetailActivity
import net.parkerstevens.imgurdemo.features.main.MainActivity
import net.parkerstevens.imgurdemo.injection.PerActivity
import net.parkerstevens.imgurdemo.injection.module.ActivityModule

@PerActivity
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(baseActivity: BaseActivity)

    fun inject(mainActivity: MainActivity)

    fun inject(detailActivity: DetailActivity)
}
