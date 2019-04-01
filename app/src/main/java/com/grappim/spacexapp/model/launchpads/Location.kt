package com.grappim.spacexapp.model.launchpads

import com.google.gson.annotations.SerializedName

data class Location(
  @SerializedName("latitude")
  val latitude: Double?,
  @SerializedName("longitude")
  val longitude: Double?,
  @SerializedName("name")
  val name: String?,
  @SerializedName("region")
  val region: String?
)