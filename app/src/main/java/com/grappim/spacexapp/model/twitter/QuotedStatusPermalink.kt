package com.grappim.spacexapp.model.twitter


import com.google.gson.annotations.SerializedName

data class QuotedStatusPermalink(
  @SerializedName("display")
  val display: String?,
  @SerializedName("expanded")
  val expanded: String?,
  @SerializedName("url")
  val url: String?
)