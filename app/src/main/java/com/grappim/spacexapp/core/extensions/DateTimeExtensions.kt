package com.grappim.spacexapp.core.extensions

import com.grappim.spacexapp.core.utils.DateTimeUtils
import org.threeten.bp.*
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.DateTimeParseException
import org.threeten.bp.temporal.ChronoField

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
      .withOffsetSameInstant(DateTimeUtils.getZoneOffset(inUtc))
  }

fun String.getOffsetDateTimeFromString(
  inUtc: Boolean = false
): OffsetDateTime =
  OffsetDateTime
    .parse(this)
    .withOffsetSameInstant(DateTimeUtils.getZoneOffset(inUtc))

fun String.getOffsetDateTimeWithFormatter(
  inUtc: Boolean = true,
  formatter: DateTimeFormatter
): OffsetDateTime {
  val ldt = LocalDateTime.parse(this, formatter)
  val zdt = ldt.atZone(DateTimeUtils.getZoneOffset(inUtc))
  return zdt.toOffsetDateTime()
}

fun OffsetDateTime.toUtc(): OffsetDateTime =
  this.withOffsetSameInstant(DateTimeUtils.getZoneOffset(true))

fun OffsetDateTime.toTheLocalDateTime(): OffsetDateTime =
  this.withOffsetSameInstant(DateTimeUtils.getZoneOffset(false))

fun OffsetDateTime.getEpochMilli(): Long = this.toInstant().toEpochMilli()

fun OffsetDateTime.getNanoOfSecond(): Long = this.getLong(ChronoField.NANO_OF_SECOND)
