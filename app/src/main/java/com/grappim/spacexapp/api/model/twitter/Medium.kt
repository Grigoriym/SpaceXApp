package com.grappim.spacexapp.api.model.twitter

import com.google.gson.annotations.SerializedName

data class Medium(
  @SerializedName("h")
  val h: Int?,
  @SerializedName("resize")
  val resize: String?,
  @SerializedName("w")
  val w: Int?
)