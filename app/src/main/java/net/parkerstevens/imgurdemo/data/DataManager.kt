package net.parkerstevens.imgurdemo.data

import io.reactivex.Single
import net.parkerstevens.imgurdemo.data.model.ImgurImage
import net.parkerstevens.imgurdemo.data.model.Imgurs
import net.parkerstevens.imgurdemo.data.remote.ImgurApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManager @Inject
constructor(private val imgurApi: ImgurApi) {
    private val auth : String = "Client-ID 126701cd8332f32"
    fun getImgurPage(page: Int, search: String): Single<List<ImgurImage>> {
        return imgurApi.getImgurPage(page, search, auth)
                .toObservable()
                .flatMapIterable { t: Imgurs -> t.data }
                .filter { t: ImgurImage -> t.isAlbum != true }
                .toList()
    }
}