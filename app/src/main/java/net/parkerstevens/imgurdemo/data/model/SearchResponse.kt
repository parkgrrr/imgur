package net.parkerstevens.imgurdemo.data.model

import android.annotation.SuppressLint
import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

data class Imgurs(
        @Json(name = "data")
        var data: List<ImgurImage>) {
    @Json(name = "success")
    var success: Boolean? = null
    @Json(name = "status")
    var status: Int? = null

}

@Parcelize
@SuppressLint("ParcelCreator")
data class ImgurImage(var id: String,
                      @Json(name = "title")
                      var title: String?,
                      @Json(name = "link")
                      var link: String = "") : Parcelable {

    @Json(name = "description")
    var description: Any? = null
    @Json(name = "datetime")
    var datetime: Int? = null
    @Json(name = "cover")
    var cover: String? = null
    @Json(name = "cover_width")
    var coverWidth: Int? = null
    @Json(name = "cover_height")
    var coverHeight: Int? = null
    @Json(name = "account_url")
    var accountUrl: String? = null
    @Json(name = "account_id")
    var accountId: Int? = null
    @Json(name = "privacy")
    var privacy: String? = null
    @Json(name = "layout")
    var layout: String? = null
    @Json(name = "views")
    var views: Int? = null
    @Json(name = "ups")
    var ups: Int? = null
    @Json(name = "downs")
    var downs: Int? = null
    @Json(name = "points")
    var points: Int? = null
    @Json(name = "score")
    var score: Int? = null
    @Json(name = "is_album")
    var isAlbum: Boolean? = null
    @Json(name = "vote")
    var vote: Any? = null
    @Json(name = "favorite")
    var favorite: Boolean? = null
    @Json(name = "nsfw")
    var nsfw: Boolean? = null
    @Json(name = "section")
    var section: String? = null
    @Json(name = "comment_count")
    var commentCount: Int? = null
    @Json(name = "favorite_count")
    var favoriteCount: Int? = null
    @Json(name = "topic")
    var topic: String? = null
    @Json(name = "topic_id")
    var topicId: Int? = null
    @Json(name = "images_count")
    var imagesCount: Int? = null
    @Json(name = "in_gallery")
    var inGallery: Boolean? = null
    @Json(name = "is_ad")
    var isAd: Boolean? = null
    @Json(name = "tags")
    var tags: List<ImageTag>? = null
    @Json(name = "ad_type")
    var adType: Int? = null
    @Json(name = "ad_url")
    var adUrl: String? = null
    @Json(name = "in_most_viral")
    var inMostViral: Boolean? = null
    @Json(name = "images")
    var images: List<ImageData>? = null

}

data class ImageData(@Json(name = "id")
                 var id: String? = null) {

    @Json(name = "title")
    var title: Any? = null
    @Json(name = "description")
    var description: String? = null
    @Json(name = "datetime")
    var datetime: Int? = null
    @Json(name = "type")
    var type: String? = null
    @Json(name = "animated")
    var animated: Boolean? = null
    @Json(name = "width")
    var width: Int? = null
    @Json(name = "height")
    var height: Int? = null
    @Json(name = "size")
    var size: Int? = null
    @Json(name = "views")
    var views: Int? = null
    @Json(name = "bandwidth")
    var bandwidth: Long? = null
    @Json(name = "vote")
    var vote: Any? = null
    @Json(name = "favorite")
    var favorite: Boolean? = null
    @Json(name = "nsfw")
    var nsfw: Any? = null
    @Json(name = "section")
    var section: Any? = null
    @Json(name = "account_url")
    var accountUrl: Any? = null
    @Json(name = "account_id")
    var accountId: Any? = null
    @Json(name = "is_ad")
    var isAd: Boolean? = null
    @Json(name = "in_most_viral")
    var inMostViral: Boolean? = null
    @Json(name = "has_sound")
    var hasSound: Boolean? = null
    @Json(name = "tags")
    var tags: List<Any>? = null
    @Json(name = "ad_type")
    var adType: Int? = null
    @Json(name = "ad_url")
    var adUrl: String? = null
    @Json(name = "in_gallery")
    var inGallery: Boolean? = null
    @Json(name = "link")
    var link: String? = null
    @Json(name = "comment_count")
    var commentCount: Any? = null
    @Json(name = "favorite_count")
    var favoriteCount: Any? = null
    @Json(name = "ups")
    var ups: Any? = null
    @Json(name = "downs")
    var downs: Any? = null
    @Json(name = "points")
    var points: Any? = null
    @Json(name = "score")
    var score: Any? = null

}

data class ImageTag(@Json(name = "name")
          var name: String? = null) {
    @Json(name = "display_name")
    var displayName: String? = null
    @Json(name = "followers")
    var followers: Int? = null
    @Json(name = "total_items")
    var totalItems: Int? = null
    @Json(name = "following")
    var following: Boolean? = null
    @Json(name = "background_hash")
    var backgroundHash: String? = null
    @Json(name = "thumbnail_hash")
    var thumbnailHash: Any? = null
    @Json(name = "accent")
    var accent: String? = null
    @Json(name = "background_is_animated")
    var backgroundIsAnimated: Boolean? = null
    @Json(name = "thumbnail_is_animated")
    var thumbnailIsAnimated: Boolean? = null
    @Json(name = "is_promoted")
    var isPromoted: Boolean? = null
    @Json(name = "description")
    var description: String? = null
    @Json(name = "logo_hash")
    var logoHash: Any? = null
    @Json(name = "logo_destination_url")
    var logoDestinationUrl: Any? = null
}