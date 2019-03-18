package com.grappim.spacexapp.model.rocket

import com.google.gson.annotations.SerializedName

data class CompositeFairing(
  @SerializedName("diameter")
  val diameter: Diameter?,
  @SerializedName("height")
  val height: Height?
)