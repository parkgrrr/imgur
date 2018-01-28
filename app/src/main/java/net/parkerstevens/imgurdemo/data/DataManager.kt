package net.parkerstevens.imgurdemo.data

import io.reactivex.Single
import net.parkerstevens.imgurdemo.data.model.Imgurs
import net.parkerstevens.imgurdemo.data.remote.ImgurApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManager @Inject
constructor(private val imgurApi: ImgurApi) {
    private val auth : String = "Client-ID 126701cd8332f32"
    fun getImgurPage(page: Int, search: String): Single<Imgurs> {
        return imgurApi.getImgurPage(page, search, auth)
    }
}