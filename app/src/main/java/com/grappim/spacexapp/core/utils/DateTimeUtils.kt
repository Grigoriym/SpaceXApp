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

  fun getDateTimeFormatter3():DateTimeFormatter =
    DateTimeFormatter.ofPattern("MMM d ''yy 'at' HH:mm zzz")

  fun getOffsetTimeZone(inUtc: Boolean): ZoneOffset =
    when (inUtc) {
      true -> ZoneOffset.UTC
      else -> ZoneOffset.from(OffsetDateTime.now().offset)
    }

}