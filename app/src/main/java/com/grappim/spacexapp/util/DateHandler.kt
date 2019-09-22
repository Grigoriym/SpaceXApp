package com.grappim.spacexapp.util

import java.text.SimpleDateFormat
import java.util.*

const val dateTimePattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
const val dtpyyyyMMdd = "yyyy-MM-dd"
const val twitterDateFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy"
const val capsuleFormattedDate = "MMM d ''yy 'at' HH:mm zzz"

private val formatter = SimpleDateFormat(dtpyyyyMMdd, Locale.getDefault())
private val sdf = SimpleDateFormat("MMM d ''yy", Locale.getDefault())

private val sdfTwitter = SimpleDateFormat("MMM dd", Locale.ENGLISH)
private val formatterTwitter = SimpleDateFormat(twitterDateFormat, Locale.ENGLISH)

private val capsuleSdf = SimpleDateFormat(capsuleFormattedDate, Locale.getDefault())
private val capsuleFormatter = SimpleDateFormat(dateTimePattern, Locale.getDefault())

fun getFormattedyyyyMMdd(value: String): String =
  sdf.format(formatter.parse(value) ?: Date())

fun getDateForCapsule(value: String): String =
  capsuleSdf.format(capsuleFormatter.parse(value) ?: Date())

fun getTwitterDate(date: String?): String? =
  sdfTwitter.format(formatterTwitter.parse(date))

private const val nextLaunchPattern = "dd MMM ''yy - HH:mm"
private val nextLaunchFromFormatter = SimpleDateFormat(nextLaunchPattern, Locale.US)
private val nextLaunchToFormatter = SimpleDateFormat(dateTimePattern, Locale.US)

fun getNextLaunchUtcTime(value: String?): String =
  nextLaunchFromFormatter.format(nextLaunchToFormatter.parse(value))