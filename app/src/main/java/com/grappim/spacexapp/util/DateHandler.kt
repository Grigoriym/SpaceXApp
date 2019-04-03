package com.grappim.spacexapp.util

import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.text.SimpleDateFormat
import java.util.*

const val dateTimePattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
const val dtpyyyyMMdd = "yyyy-MM-dd"

private val formatter = SimpleDateFormat(dtpyyyyMMdd, Locale.getDefault())
private val sdf = SimpleDateFormat("MMM d ''yy", Locale.getDefault())

fun getFormattedyyyyMMdd(value: String): String =
  sdf.format(formatter.parse(value))

fun getFormattedDate(value: String?): String {
  return formatDate("MMM d ''yy 'at' HH:mm zzz", value)
}

private fun getDate(isoDateString: String?, zoneId: ZoneId): ZonedDateTime {
  val dtf = DateTimeFormatter.ofPattern(dateTimePattern).withZone(zoneId)
  return ZonedDateTime.parse(isoDateString, dtf)
}

private fun formatDate(format: String, isoDateString: String?): String =
  DateTimeFormatter.ofPattern(format).format(getDate(isoDateString, getZoneId(true)))

private fun getZoneId(showLocal: Boolean): ZoneId {
  return if (showLocal) {
    ZoneId.systemDefault()
  } else {
    ZoneId.of("Z")
  }
}