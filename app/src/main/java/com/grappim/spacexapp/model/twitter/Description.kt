package com.grappim.spacexapp.model.twitter


import com.google.gson.annotations.SerializedName

data class Description(
  @SerializedName("urls")
  val urls: List<Any?>?
)