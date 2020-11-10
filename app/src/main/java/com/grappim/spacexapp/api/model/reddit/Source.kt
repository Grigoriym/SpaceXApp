package com.grappim.spacexapp.api.model.reddit


import com.google.gson.annotations.SerializedName

data class Source(
  @SerializedName("height")
  val height: Int?,
  @SerializedName("url")
  val url: String?,
  @SerializedName("width")
  val width: Int?
)