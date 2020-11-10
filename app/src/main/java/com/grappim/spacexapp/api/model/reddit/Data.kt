package com.grappim.spacexapp.api.model.reddit

data class Data(

    val after: String?,
    val before: Any?,
    val children: List<RedditChildren>,
    val dist: Int?,
    val modhash: String?
)