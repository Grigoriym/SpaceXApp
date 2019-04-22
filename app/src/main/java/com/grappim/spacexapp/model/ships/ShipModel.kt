package com.grappim.spacexapp.model.ships

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.grappim.spacexapp.model.capsule.Mission
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ShipModel(
  @SerializedName("abs")
  val abs: Int?,
  @SerializedName("active")
  val active: Boolean?,
  @SerializedName("attempted_landings")
  val attemptedLandings: Int?,
  @SerializedName("class")
  val classX: Int?,
  @SerializedName("course_deg")
  val courseDeg: Int?,
  @SerializedName("home_port")
  val homePort: String?,
  @SerializedName("image")
  val image: String?,
  @SerializedName("imo")
  val imo: Int?,
  @SerializedName("missions")
  val missions: List<Mission>?,
  @SerializedName("mmsi")
  val mmsi: Int?,
  @SerializedName("position")
  val position: Position?,
  @SerializedName("roles")
  val roles: List<String?>?,
  @SerializedName("ship_id")
  val shipId: String?,
  @SerializedName("ship_model")
  val shipModel: String?,
  @SerializedName("ship_name")
  val shipName: String?,
  @SerializedName("ship_type")
  val shipType: String?,
  @SerializedName("speed_kn")
  val speedKn: Double?,
  @SerializedName("status")
  val status: String?,
  @SerializedName("successful_landings")
  val successfulLandings: Int?,
  @SerializedName("url")
  val url: String?,
  @SerializedName("weight_kg")
  val weightKg: Int?,
  @SerializedName("weight_lbs")
  val weightLbs: Int?,
  @SerializedName("year_built")
  val yearBuilt: Int?
) : Parcelable