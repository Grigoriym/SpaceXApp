package com.grappim.spacexapp.api.model.launches

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Fairings(
  @SerializedName("recovered")
  val recovered: Boolean?,
  @SerializedName("recovery_attempt")
  val recoveryAttempt: Boolean?,
  @SerializedName("reused")
  val reused: Boolean?,
  @SerializedName("ship")
  val ship: String?
):Parcelable