package net.parkerstevens.imgurdemo.injection.module

import android.content.Context
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import net.parkerstevens.imgurdemo.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import javax.inject.Singleton

@Module
class NetworkModule(private val context: Context) {

    protected fun getBaseUrl() = BuildConfig.IMGUR_API_URL

    @Provides
    @Singleton
    internal fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit =
            Retrofit.Builder()
                    .baseUrl(getBaseUrl())
                    .client(okHttpClient)
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

    @Provides
    @Singleton
    internal fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            httpClientBuilder.addInterceptor(httpLoggingInterceptor)
        }
        return httpClientBuilder.build()
    }

    @Provides
    @Singleton
    internal fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
            HttpLoggingInterceptor { message ->
                Timber.d(message)
            }.setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    internal fun provideMoshi(): Moshi = Moshi
            .Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
}