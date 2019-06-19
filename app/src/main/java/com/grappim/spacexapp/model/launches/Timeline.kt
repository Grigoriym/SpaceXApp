package com.grappim.spacexapp.model.launches

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Timeline(
  @SerializedName("engine_chill")
  val engineChill: Int?,
  @SerializedName("fairing_deploy")
  val fairingDeploy: Int?,
  @SerializedName("first_stage_boostback_burn")
  val firstStageBoostbackBurn: Int?,
  @SerializedName("first_stage_entry_burn")
  val firstStageEntryBurn: Int?,
  @SerializedName("first_stage_landing")
  val firstStageLanding: Int?,
  @SerializedName("go_for_launch")
  val goForLaunch: Int?,
  @SerializedName("go_for_prop_loading")
  val goForPropLoading: Int?,
  @SerializedName("ignition")
  val ignition: Int?,
  @SerializedName("liftoff")
  val liftoff: Int?,
  @SerializedName("maxq")
  val maxq: Int?,
  @SerializedName("meco")
  val meco: Int?,
  @SerializedName("payload_deploy")
  val payloadDeploy: Int?,
  @SerializedName("prelaunch_checks")
  val prelaunchChecks: Int?,
  @SerializedName("propellant_pressurization")
  val propellantPressurization: Int?,
  @SerializedName("rp1_loading")
  val rp1Loading: Int?,
  @SerializedName("seco-1")
  val seco1: Int?,
  @SerializedName("seco-2")
  val seco2: Int?,
  @SerializedName("second_stage_ignition")
  val secondStageIgnition: Int?,
  @SerializedName("second_stage_restart")
  val secondStageRestart: Int?,
  @SerializedName("stage1_lox_loading")
  val stage1LoxLoading: Int?,
  @SerializedName("stage2_lox_loading")
  val stage2LoxLoading: Int?,
  @SerializedName("stage_sep")
  val stageSep: Int?,
  @SerializedName("webcast_liftoff")
  val webcastLiftoff: Int?
) : Parcelable