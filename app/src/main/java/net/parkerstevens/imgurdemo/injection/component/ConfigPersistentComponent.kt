package net.parkerstevens.imgurdemo.injection.component

import dagger.Component
import net.parkerstevens.imgurdemo.injection.ConfigPersistent
import net.parkerstevens.imgurdemo.injection.module.ActivityModule
/**
 * A dagger component that will live during the lifecycle of an Activity or Fragment but it won't
 * be destroy during configuration changes. Check [BaseActivity] and [BaseFragment] to
 * see how this components survives configuration changes.
 * Use the [ConfigPersistent] scope to annotate dependencies that need to survive
 * configuration changes (for example Presenters).
 */
@ConfigPersistent
@Component(dependencies = [AppComponent::class])
interface ConfigPersistentComponent {

    fun activityComponent(activityModule: ActivityModule): ActivityComponent

}
