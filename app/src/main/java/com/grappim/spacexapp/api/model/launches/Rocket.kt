package com.grappim.spacexapp.api.model.launches

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Rocket(
  @SerializedName("fairings")
  val fairings: Fairings?,
  @SerializedName("first_stage")
  val firstStage: FirstStage?,
  @SerializedName("rocket_id")
  val rocketId: String?,
  @SerializedName("rocket_name")
  val rocketName: String?,
  @SerializedName("rocket_type")
  val rocketType: String?,
  @SerializedName("second_stage")
  val secondStage: SecondStage?
) : Parcelable