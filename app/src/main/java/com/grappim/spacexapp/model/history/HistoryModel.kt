package com.grappim.spacexapp.model.history

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HistoryModel(
  @SerializedName("details")
  val details: String?,
  @SerializedName("event_date_unix")
  val eventDateUnix: Int?,
  @SerializedName("event_date_utc")
  val eventDateUtc: String?,
  @SerializedName("flight_number")
  val flightNumber: Int?,
  @SerializedName("id")
  val id: Int?,
  @SerializedName("links")
  val links: Links?,
  @SerializedName("title")
  val title: String?
) : Parcelable