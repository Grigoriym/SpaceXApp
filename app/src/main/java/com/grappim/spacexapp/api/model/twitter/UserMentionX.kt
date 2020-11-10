package com.grappim.spacexapp.api.model.twitter


import com.google.gson.annotations.SerializedName

data class UserMentionX(
  @SerializedName("id")
  val id: Int?,
  @SerializedName("id_str")
  val idStr: String?,
  @SerializedName("indices")
  val indices: List<Int?>?,
  @SerializedName("name")
  val name: String?,
  @SerializedName("screen_name")
  val screenName: String?
)