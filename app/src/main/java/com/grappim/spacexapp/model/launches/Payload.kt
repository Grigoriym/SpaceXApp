package com.grappim.spacexapp.model.launches

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Payload(
  @SerializedName("customers")
  val customers: List<String?>?,
  @SerializedName("manufacturer")
  val manufacturer: String?,
  @SerializedName("nationality")
  val nationality: String?,
  @SerializedName("norad_id")
  val noradId: List<Int?>?,
  @SerializedName("orbit")
  val orbit: String?,
  @SerializedName("orbit_params")
  val orbitParams: OrbitParams?,
  @SerializedName("payload_id")
  val payloadId: String?,
  @SerializedName("payload_mass_kg")
  val payloadMassKg: Double?,
  @SerializedName("payload_mass_lbs")
  val payloadMassLbs: Double?,
  @SerializedName("payload_type")
  val payloadType: String?,
  @SerializedName("reused")
  val reused: Boolean?
) : Parcelable