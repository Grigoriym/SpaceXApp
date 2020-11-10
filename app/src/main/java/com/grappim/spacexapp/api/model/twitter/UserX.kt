package com.grappim.spacexapp.api.model.twitter


import com.google.gson.annotations.SerializedName

data class UserX(
  @SerializedName("protected")
  val `protected`: Boolean?,
  @SerializedName("contributors_enabled")
  val contributorsEnabled: Boolean?,
  @SerializedName("created_at")
  val createdAt: String?,
  @SerializedName("default_profile")
  val defaultProfile: Boolean?,
  @SerializedName("default_profile_image")
  val defaultProfileImage: Boolean?,
  @SerializedName("description")
  val description: String?,
  @SerializedName("entities")
  val entities: Entities?,
  @SerializedName("favourites_count")
  val favouritesCount: Int?,
  @SerializedName("follow_request_sent")
  val followRequestSent: Boolean?,
  @SerializedName("followers_count")
  val followersCount: Int?,
  @SerializedName("following")
  val following: Boolean?,
  @SerializedName("friends_count")
  val friendsCount: Int?,
  @SerializedName("geo_enabled")
  val geoEnabled: Boolean?,
  @SerializedName("has_extended_profile")
  val hasExtendedProfile: Boolean?,
  @SerializedName("id")
  val id: Long?,
  @SerializedName("id_str")
  val idStr: String?,
  @SerializedName("is_translation_enabled")
  val isTranslationEnabled: Boolean?,
  @SerializedName("is_translator")
  val isTranslator: Boolean?,
  @SerializedName("lang")
  val lang: Any?,
  @SerializedName("listed_count")
  val listedCount: Int?,
  @SerializedName("location")
  val location: String?,
  @SerializedName("name")
  val name: String?,
  @SerializedName("notifications")
  val notifications: Boolean?,
  @SerializedName("profile_background_color")
  val profileBackgroundColor: String?,
  @SerializedName("profile_background_image_url")
  val profileBackgroundImageUrl: String?,
  @SerializedName("profile_background_image_url_https")
  val profileBackgroundImageUrlHttps: String?,
  @SerializedName("profile_background_tile")
  val profileBackgroundTile: Boolean?,
  @SerializedName("profile_banner_url")
  val profileBannerUrl: String?,
  @SerializedName("profile_image_url")
  val profileImageUrl: String?,
  @SerializedName("profile_image_url_https")
  val profileImageUrlHttps: String?,
  @SerializedName("profile_link_color")
  val profileLinkColor: String?,
  @SerializedName("profile_sidebar_border_color")
  val profileSidebarBorderColor: String?,
  @SerializedName("profile_sidebar_fill_color")
  val profileSidebarFillColor: String?,
  @SerializedName("profile_text_color")
  val profileTextColor: String?,
  @SerializedName("profile_use_background_image")
  val profileUseBackgroundImage: Boolean?,
  @SerializedName("screen_name")
  val screenName: String?,
  @SerializedName("statuses_count")
  val statusesCount: Int?,
  @SerializedName("time_zone")
  val timeZone: Any?,
  @SerializedName("translator_type")
  val translatorType: String?,
  @SerializedName("url")
  val url: String?,
  @SerializedName("utc_offset")
  val utcOffset: Any?,
  @SerializedName("verified")
  val verified: Boolean?
)