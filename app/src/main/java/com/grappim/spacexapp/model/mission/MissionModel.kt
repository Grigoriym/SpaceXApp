package com.grappim.spacexapp.model.mission

import com.google.gson.annotations.SerializedName

data class MissionModel(
  @SerializedName("description")
  val description: String?,
  @SerializedName("manufacturers")
  val manufacturers: List<String?>?,
  @SerializedName("mission_id")
  val missionId: String?,
  @SerializedName("mission_name")
  val missionName: String?,
  @SerializedName("payload_ids")
  val payloadIds: List<String?>?,
  @SerializedName("twitter")
  val twitter: String?,
  @SerializedName("website")
  val website: String?,
  @SerializedName("wikipedia")
  val wikipedia: String?
)