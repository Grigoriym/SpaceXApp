package com.grappim.spacexapp.model.launches


import com.google.gson.annotations.SerializedName

data class FirstStage(
  @SerializedName("cores")
  val cores: List<Core?>?
)