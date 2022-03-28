package com.grappim.spacexapp.api.model.twitter

import com.google.gson.annotations.SerializedName

data class User(
  @SerializedName("created_at")
  val createdAt: String?,
  @SerializedName("default_profile")
  val defaultProfile: Boolean?,
  @SerializedName("default_profile_image")
  val defaultProfileImage: Boolean?,
  @SerializedName("description")
  val description: String?,
  @SerializedName("follow_request_sent")
  val followRequestSent: Boolean?,
  @SerializedName("followers_count")
  val followersCount: Int?,
  @SerializedName("following")
  val following: Boolean?,
  @SerializedName("friends_count")
  val friendsCount: Int?,
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
  @SerializedName("listed_count")
  val listedCount: Int?,
  @SerializedName("location")
  val location: String?,
  @SerializedName("name")
  val name: String?,
  @SerializedName("notifications")
  val notifications: Boolean?,
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
  @SerializedName("profile_use_background_image")
  val profileUseBackgroundImage: Boolean?,
  @SerializedName("screen_name")
  val screenName: String?,
  @SerializedName("statuses_count")
  val statusesCount: Int?,
  @SerializedName("url")
  val url: String?
)