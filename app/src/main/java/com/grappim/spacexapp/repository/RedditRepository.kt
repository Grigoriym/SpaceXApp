package com.grappim.spacexapp.repository

import com.grappim.spacexapp.model.reddit.RedditModel
import com.grappim.spacexapp.pagination.Listing

interface RedditRepository {

  fun getPostsBySubreddit(sureddit: String): Listing<RedditModel>
}