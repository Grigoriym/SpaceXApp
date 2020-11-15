package com.grappim.spacexapp.api.model.twitter

import com.google.gson.annotations.SerializedName

data class Status(
  @SerializedName("created_at")
  val createdAt: String?,
  @SerializedName("display_text_range")
  val displayTextRange: List<Int>?,
  @SerializedName("extended_entities")
  val extendedEntities: ExtendedEntities?,
  @SerializedName("favorite_count")
  val favoriteCount: Int?,
  @SerializedName("full_text")
  val fullText: String?,
  @SerializedName("id")
  val id: Long?,
  @SerializedName("id_str")
  val idStr: String?,
  @SerializedName("is_quote_status")
  val isQuoteStatus: Boolean?,
  @SerializedName("lang")
  val lang: String?,
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