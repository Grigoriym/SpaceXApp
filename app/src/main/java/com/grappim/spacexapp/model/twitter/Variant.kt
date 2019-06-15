package com.grappim.spacexapp.model.twitter


import com.google.gson.annotations.SerializedName

data class Variant(
  @SerializedName("bitrate")
  val bitrate: Int?,
  @SerializedName("content_type")
  val contentType: String?,
  @SerializedName("url")
  val url: String?
)