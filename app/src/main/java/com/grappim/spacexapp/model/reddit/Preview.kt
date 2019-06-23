package com.grappim.spacexapp.model.reddit


import com.google.gson.annotations.SerializedName

data class Preview(
  @SerializedName("enabled")
  val enabled: Boolean?,
  @SerializedName("images")
  val images: List<Image?>?
)