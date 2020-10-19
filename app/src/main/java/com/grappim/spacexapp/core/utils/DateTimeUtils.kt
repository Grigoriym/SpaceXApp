package com.grappim.spacexapp.core.utils

import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneOffset
import org.threeten.bp.format.DateTimeFormatter

object DateTimeUtils {

  fun getDateTimeFormatter(): DateTimeFormatter =
    DateTimeFormatter.ofPattern("dd:MM:yyyy HH:mm:ss")

  fun getDateTimeFormatter1(): DateTimeFormatter =
    DateTimeFormatter.ofPattern("MMM d ''yy")

  fun getDateTimeFormatter2(): DateTimeFormatter =
    DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneOffset.UTC)

  fun getDateTimeFormatter3(): DateTimeFormatter =
    DateTimeFormatter.ofPattern("MMM d ''yy 'at' HH:mm")

  fun getDateTimeFormatter4(): DateTimeFormatter =
    DateTimeFormatter.ofPattern("dd MMM ''yy - HH:mm")

  fun getDateTimeFormatter5(): DateTimeFormatter =
    DateTimeFormatter.ofPattern("MMM dd")

  fun getZoneOffset(inUtc: Boolean): ZoneOffset {
    return if (inUtc) {
      ZoneOffset.UTC
    } else {
      getCurrentZoneOffset()
    }
  }

  fun getNowOffsetDateTime(
    inUtc: Boolean = false
  ): OffsetDateTime =
    OffsetDateTime.now(getZoneOffset(inUtc))

  private fun getCurrentZoneOffset(): ZoneOffset = OffsetDateTime.now().offset

}