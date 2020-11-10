package com.grappim.spacexapp.api.model.launches

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OrbitParams(
  @SerializedName("apoapsis_km")
  val apoapsisKm: Double?,
  @SerializedName("arg_of_pericenter")
  val argOfPericenter: Double?,
  @SerializedName("eccentricity")
  val eccentricity: Double?,
  @SerializedName("epoch")
  val epoch: String?,
  @SerializedName("inclination_deg")
  val inclinationDeg: Double?,
  @SerializedName("lifespan_years")
  val lifespanYears: Double?,
  @SerializedName("longitude")
  val longitude: Double?,
  @SerializedName("mean_anomaly")
  val meanAnomaly: Double?,
  @SerializedName("mean_motion")
  val meanMotion: Double?,
  @SerializedName("periapsis_km")
  val periapsisKm: Double?,
  @SerializedName("period_min")
  val periodMin: Double?,
  @SerializedName("raan")
  val raan: Double?,
  @SerializedName("reference_system")
  val referenceSystem: String?,
  @SerializedName("regime")
  val regime: String?,
  @SerializedName("semi_major_axis_km")
  val semiMajorAxisKm: Double?
) : Parcelable