package com.grappim.spacexapp.model.twitter


import com.google.gson.annotations.SerializedName

data class UrlXX(
  @SerializedName("display_url")
  val displayUrl: String?,
  @SerializedName("expanded_url")
  val expandedUrl: String?,
  @SerializedName("indices")
  val indices: List<Int?>?,
  @SerializedName("url")
  val url: String?
)