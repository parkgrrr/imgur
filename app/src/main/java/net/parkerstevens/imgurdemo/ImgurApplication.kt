package net.parkerstevens.imgurdemo

import android.app.Application
import android.content.Context
import net.parkerstevens.imgurdemo.injection.component.AppComponent
import net.parkerstevens.imgurdemo.injection.component.DaggerAppComponent
import net.parkerstevens.imgurdemo.injection.module.AppModule
import net.parkerstevens.imgurdemo.injection.module.NetworkModule
import timber.log.Timber

class ImgurApplication : Application() {
    private var appComponent: AppComponent? = null

    companion object {
        operator fun get(context: Context): ImgurApplication {
            return context.applicationContext as ImgurApplication
        }
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    // Needed to replace the component with a test specific one
    var component: AppComponent
        get() {
            if (appComponent == null) {
                appComponent = DaggerAppComponent.builder()
                        .appModule(AppModule(this))
                        .networkModule(NetworkModule(this))
                        .build()
            }
            return appComponent as AppComponent
        }
        set(appComponent) {
            this.appComponent = appComponent
        }

}