package com.grappim.spacexapp.model.cores

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.grappim.spacexapp.model.capsule.Mission
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CoreModel(
  @SerializedName("asds_attempts")
  val asdsAttempts: Int?,
  @SerializedName("asds_landings")
  val asdsLandings: Int?,
  @SerializedName("block")
  val block: Int?,
  @SerializedName("core_serial")
  val coreSerial: String?,
  @SerializedName("details")
  val details: String?,
  @SerializedName("missions")
  val missions: List<Mission?>?,
  @SerializedName("original_launch")
  val originalLaunch: String?,
  @SerializedName("original_launch_unix")
  val originalLaunchUnix: Int?,
  @SerializedName("reuse_count")
  val reuseCount: Int?,
  @SerializedName("rtls_attempts")
  val rtlsAttempts: Int?,
  @SerializedName("rtls_landings")
  val rtlsLandings: Int?,
  @SerializedName("status")
  val status: String?,
  @SerializedName("water_landing")
  val waterLanding: Boolean?
) : Parcelable