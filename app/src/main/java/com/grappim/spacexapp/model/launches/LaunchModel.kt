package com.grappim.spacexapp.model.launches

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LaunchModel(
  @SerializedName("details")
  val details: String?,
  @SerializedName("flight_number")
  val flightNumber: Int?,
  @SerializedName("is_tentative")
  val isTentative: Boolean?,
  @SerializedName("launch_date_local")
  val launchDateLocal: String?,
  @SerializedName("launch_date_unix")
  val launchDateUnix: Int?,
  @SerializedName("launch_date_utc")
  val launchDateUtc: String?,
  @SerializedName("launch_failure_details")
  val launchFailureDetails: LaunchFailureDetails?,
  @SerializedName("launch_site")
  val launchSite: LaunchSite?,
  @SerializedName("launch_success")
  val launchSuccess: Boolean?,
  @SerializedName("launch_window")
  val launchWindow: Int?,
  @SerializedName("launch_year")
  val launchYear: String?,
  @SerializedName("links")
  val links: Links?,
  @SerializedName("mission_id")
  val missionId: List<String?>?,
  @SerializedName("mission_name")
  val missionName: String?,
  @SerializedName("rocket")
  val rocket: Rocket?,
  @SerializedName("ships")
  val ships: List<String?>?,
  @SerializedName("static_fire_date_unix")
  val staticFireDateUnix: Long?,
  @SerializedName("static_fire_date_utc")
  val staticFireDateUtc: String?,
  @SerializedName("tbd")
  val tbd: Boolean?,
  @SerializedName("telemetry")
  val telemetry: Telemetry?,
  @SerializedName("tentative_max_precision")
  val tentativeMaxPrecision: String?,
  @SerializedName("timeline")
  val timeline: Timeline?,
  @SerializedName("upcoming")
  val upcoming: Boolean?
) : Parcelable {
  companion object {
    fun empty() = LaunchModel(
      "", 0, false, "", 0,
      "", null, null, null,
      null, "", null, emptyList(), "",
      null, emptyList(), null, null, false,
      null, "", null, false
    )
  }
}