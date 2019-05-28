package com.grappim.spacexapp.model.twitter


import com.google.gson.annotations.SerializedName

data class EntitiesX(
  @SerializedName("description")
  val description: Description?,
  @SerializedName("url")
  val url: Url?
)