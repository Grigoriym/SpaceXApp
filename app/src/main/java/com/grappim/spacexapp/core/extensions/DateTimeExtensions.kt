package com.grappim.spacexapp.core.extensions

import com.grappim.spacexapp.core.utils.DateTimeUtils
import org.threeten.bp.*
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.DateTimeParseException

@Throws(DateTimeParseException::class)
fun String?.getOffsetDateTime(
  inUtc: Boolean = true,
  formatter: DateTimeFormatter? = null
): OffsetDateTime =
  formatter?.let {
    val ldt = LocalDate.parse(this, it)
    val zdt = ldt.atTime(LocalTime.MIN).atZone(ZoneOffset.UTC)
    zdt.toOffsetDateTime()
  } ?: let {
    OffsetDateTime
      .parse(this)
      .withOffsetSameInstant(DateTimeUtils.getOffsetTimeZone(inUtc))
  }
