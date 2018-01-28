package net.parkerstevens.imgurdemo.injection.component

import android.app.Application
import android.content.Context
import dagger.Component
import net.parkerstevens.imgurdemo.data.DataManager
import net.parkerstevens.imgurdemo.data.remote.ImgurApi
import net.parkerstevens.imgurdemo.injection.ApplicationContext
import net.parkerstevens.imgurdemo.injection.module.AppModule
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    @ApplicationContext
    fun context(): Context

    fun application(): Application

    fun dataManager(): DataManager

    fun imgurApi(): ImgurApi
}