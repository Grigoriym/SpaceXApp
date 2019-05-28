package com.grappim.spacexapp.model.twitter


import com.google.gson.annotations.SerializedName

data class Url(
  @SerializedName("urls")
  val urls: List<UrlX?>?
)