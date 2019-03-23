package com.grappim.spacexapp.model.rocket

import com.google.gson.annotations.SerializedName

data class Height(
  @SerializedName("feet")
  val feet: Double?,
  @SerializedName("meters")
  val meters: Double?
)