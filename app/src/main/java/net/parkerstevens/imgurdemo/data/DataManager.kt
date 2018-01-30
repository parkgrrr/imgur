package net.parkerstevens.imgurdemo.data

import io.reactivex.Single
import net.parkerstevens.imgurdemo.BuildConfig
import net.parkerstevens.imgurdemo.data.model.ImgurImage
import net.parkerstevens.imgurdemo.data.model.Imgurs
import net.parkerstevens.imgurdemo.data.remote.ImgurApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManager @Inject
constructor(private val imgurApi: ImgurApi) {
    companion object {
        const val auth: String = BuildConfig.IMGUR_API_KEY
    }

    fun getImgurPage(page: Int, search: String): Single<List<ImgurImage>> {
        return imgurApi.getImgurPage(page, search, auth)
                .toObservable()
                .flatMapIterable { t: Imgurs -> t.data }
                .toList()
    }
}