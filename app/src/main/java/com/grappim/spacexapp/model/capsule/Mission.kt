package com.grappim.spacexapp.model.capsule

import com.google.gson.annotations.SerializedName

data class Mission(
  @SerializedName("flight")
  val flight: Int?,
  @SerializedName("name")
  val name: String?
)