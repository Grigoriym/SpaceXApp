package com.grappim.spacexapp.api.model.capsule

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CapsuleModel(
  @SerializedName("capsule_id")
  val capsuleId: String?,
  @SerializedName("capsule_serial")
  val capsuleSerial: String?,
  @SerializedName("details")
  val details: String?,
  @SerializedName("landings")
  val landings: Int?,
  @SerializedName("missions")
  val missions: List<Mission>?,
  @SerializedName("original_launch")
  val originalLaunch: String?,
  @SerializedName("original_launch_unix")
  val originalLaunchUnix: Long?,
  @SerializedName("reuse_count")
  val reuseCount: Int?,
  @SerializedName("status")
  val status: String?,
  @SerializedName("type")
  val type: String?
) : Parcelable