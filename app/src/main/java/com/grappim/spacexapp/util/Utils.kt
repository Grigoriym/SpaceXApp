package com.grappim.spacexapp.util

import kotlin.random.Random

private val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

fun getRandomStringOfSize(size: Int): String =
  (1..size)
    .map { Random.nextInt(0, charPool.size) }
    .map(charPool::get)
    .joinToString("")
