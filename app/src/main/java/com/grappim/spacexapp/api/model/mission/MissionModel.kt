package com.grappim.spacexapp.api.model.mission

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
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
) : Parcelable