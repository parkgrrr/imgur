package net.parkerstevens.imgurdemo.injection.module

import dagger.Module
import dagger.Provides
import net.parkerstevens.imgurdemo.data.remote.ImgurApi
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = arrayOf(NetworkModule::class))
class ApiModule {

    @Provides
    @Singleton
    internal fun provideImgurApi(retrofit: Retrofit): ImgurApi =
            retrofit.create(ImgurApi::class.java)
}