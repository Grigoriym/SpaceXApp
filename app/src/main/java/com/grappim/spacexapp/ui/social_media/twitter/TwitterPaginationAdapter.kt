package com.grappim.spacexapp.ui.social_media.twitter

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.grappim.spacexapp.R
import com.grappim.spacexapp.api.model.twitter.TweetModel
import com.grappim.spacexapp.core.extensions.getOffsetDateTime
import com.grappim.spacexapp.core.extensions.gone
import com.grappim.spacexapp.core.extensions.inflate
import com.grappim.spacexapp.core.extensions.setSafeOnClickListener
import com.grappim.spacexapp.core.extensions.show
import com.grappim.spacexapp.core.utils.DateTimeUtils
import kotlinx.android.synthetic.main.layout_twitter_item.view.imageTweet
import kotlinx.android.synthetic.main.layout_twitter_item.view.ivTwitterItemProfileImage
import kotlinx.android.synthetic.main.layout_twitter_item.view.tvTwitterItemCreatedAt
import kotlinx.android.synthetic.main.layout_twitter_item.view.tvTwitterItemScreenName
import kotlinx.android.synthetic.main.layout_twitter_item.view.tvTwitterItemText
import kotlinx.android.synthetic.main.layout_twitter_item.view.tvTwitterName

interface TwitterAdapterClickListener {
    fun onTweetClick(tweet: TweetModel)
    fun onImageClick(tweet: TweetModel)
}

class TwitterPaginationAdapter(
    private val listener: TwitterAdapterClickListener
) : PagingDataAdapter<TweetModel, TwitterPaginationViewHolder>(MY_DIFF_UTIL) {

    companion object {
        val MY_DIFF_UTIL = object : DiffUtil.ItemCallback<TweetModel>() {
            override fun areItemsTheSame(
                oldItem: TweetModel, newItem: TweetModel
            ): Boolean = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: TweetModel, newItem: TweetModel
            ): Boolean = oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): TwitterPaginationViewHolder = TwitterPaginationViewHolder(
        parent.inflate(R.layout.layout_twitter_item)
    )

    override fun onBindViewHolder(
        holder: TwitterPaginationViewHolder,
        position: Int
    ) {
        holder.apply {
            val tweetModel = getItem(bindingAdapterPosition)

            itemView.tvTwitterItemCreatedAt.text = tweetModel?.createdAt?.let { date ->
                DateTimeUtils.getDateTimeFormatter5().format(
                    date.getOffsetDateTime(
                        formatter = DateTimeUtils.getDateTimeTwitterFormatter()
                    )
                )
            } ?: let {
                "Unknown"
            }
            itemView.tvTwitterItemScreenName.text = itemView.context.getString(
                R.string.twitter_screen_name,
                tweetModel?.user?.screenName ?: ""
            )
            itemView.tvTwitterItemText.text = tweetModel?.fullText ?: ""
            itemView.tvTwitterName.text = tweetModel?.user?.name ?: ""

            if (tweetModel?.extendedEntities?.media?.isNotEmpty() == true) {
                itemView.imageTweet.show()
                itemView.imageTweet.load(tweetModel.extendedEntities.media[0].mediaUrlHttps)
            } else {
                itemView.imageTweet.gone()
            }

            itemView.imageTweet.setSafeOnClickListener {
                listener.onImageClick(tweetModel!!)
            }
            itemView.setSafeOnClickListener { listener.onTweetClick(tweetModel!!) }
            itemView.ivTwitterItemProfileImage.load(tweetModel?.user?.profileImageUrlHttps)
        }
    }
}

class TwitterPaginationViewHolder(view: View) : RecyclerView.ViewHolder(view)