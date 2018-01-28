package net.parkerstevens.imgurdemo.data.remote


import io.reactivex.Single
import net.parkerstevens.imgurdemo.data.model.Imgurs
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ImgurApi {

    @GET("{page}")
    fun getImgurPage(@Path("page") page: Int,
                     @Query("q") search: String,
                     @Header("Authorization") auth: String): Single<Imgurs>

}
