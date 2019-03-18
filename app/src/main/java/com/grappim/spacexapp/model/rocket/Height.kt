package com.grappim.spacexapp.model.rocket

import com.google.gson.annotations.SerializedName

data class Height(
  @SerializedName("feet")
  val feet: Int?,
  @SerializedName("meters")
  val meters: Double?
)