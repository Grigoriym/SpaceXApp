package com.grappim.spacexapp.model.history

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HistoryModel(
  val details: String?,
  @SerializedName("event_date_unix")
  val eventDateUnix: Int?,
  @SerializedName("event_date_utc")
  val eventDateUtc: String?,
  @SerializedName("flight_number")
  val flightNumber: Int?,
  val id: Int?,
  val links: Links?,
  val title: String?
) : Parcelable {
    companion object {
        fun empty() = HistoryModel(
          "", 0, "", 0,
          0, null, ""
        )
    }
}