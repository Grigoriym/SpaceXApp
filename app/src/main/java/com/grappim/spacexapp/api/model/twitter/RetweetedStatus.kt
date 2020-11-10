package com.grappim.spacexapp.api.model.twitter


import com.google.gson.annotations.SerializedName

data class RetweetedStatus(
  @SerializedName("contributors")
  val contributors: Any?,
  @SerializedName("coordinates")
  val coordinates: Any?,
  @SerializedName("created_at")
  val createdAt: String?,
  @SerializedName("display_text_range")
  val displayTextRange: List<Int?>?,
  @SerializedName("entities")
  val entities: EntitiesX?,
  @SerializedName("extended_entities")
  val extendedEntities: ExtendedEntities?,
  @SerializedName("favorite_count")
  val favoriteCount: Int?,
  @SerializedName("favorited")
  val favorited: Boolean?,
  @SerializedName("full_text")
  val fullText: String?,
  @SerializedName("geo")
  val geo: Any?,
  @SerializedName("id")
  val id: Long?,
  @SerializedName("id_str")
  val idStr: String?,
  @SerializedName("in_reply_to_screen_name")
  val inReplyToScreenName: Any?,
  @SerializedName("in_reply_to_status_id")
  val inReplyToStatusId: Any?,
  @SerializedName("in_reply_to_status_id_str")
  val inReplyToStatusIdStr: Any?,
  @SerializedName("in_reply_to_user_id")
  val inReplyToUserId: Any?,
  @SerializedName("in_reply_to_user_id_str")
  val inReplyToUserIdStr: Any?,
  @SerializedName("is_quote_status")
  val isQuoteStatus: Boolean?,
  @SerializedName("lang")
  val lang: String?,
  @SerializedName("place")
  val place: Any?,
  @SerializedName("possibly_sensitive")
  val possiblySensitive: Boolean?,
  @SerializedName("retweet_count")
  val retweetCount: Int?,
  @SerializedName("retweeted")
  val retweeted: Boolean?,
  @SerializedName("source")
  val source: String?,
  @SerializedName("truncated")
  val truncated: Boolean?,
  @SerializedName("user")
  val user: User?
)