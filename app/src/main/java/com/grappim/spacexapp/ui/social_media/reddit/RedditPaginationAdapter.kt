package com.grappim.spacexapp.ui.social_media.reddit

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.grappim.spacexapp.R
import com.grappim.spacexapp.core.extensions.setSafeOnClickListener
import com.grappim.spacexapp.api.model.reddit.RedditChildren
import com.grappim.spacexapp.core.extensions.inflate
import kotlinx.android.synthetic.main.layout_reddit_item.view.imagePreview
import kotlinx.android.synthetic.main.layout_reddit_item.view.tvRedditAuthor
import kotlinx.android.synthetic.main.layout_reddit_item.view.tvRedditCreatedUTC
import kotlinx.android.synthetic.main.layout_reddit_item.view.tvRedditScore
import kotlinx.android.synthetic.main.layout_reddit_item.view.tvRedditSelftext
import kotlinx.android.synthetic.main.layout_reddit_item.view.tvRedditTitle

class RedditPaginationAdapter(
    val onClick: (RedditChildren?) -> Unit
) : PagingDataAdapter<RedditChildren, RedditPaginationViewHolder>(MY_DIFF_UTIL) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RedditPaginationViewHolder =
        RedditPaginationViewHolder(parent.inflate(R.layout.layout_reddit_item,))

    override fun onBindViewHolder(holder: RedditPaginationViewHolder, position: Int) {
        holder.apply {
            model = getItem(position)
            itemView.setSafeOnClickListener { onClick(model) }
            imagePreview.load(model?.data?.preview?.images?.get(0)?.source?.url)
        }
    }

    companion object {
        val MY_DIFF_UTIL = object : DiffUtil.ItemCallback<RedditChildren>() {
            override fun areItemsTheSame(oldItem: RedditChildren, newItem: RedditChildren): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: RedditChildren, newItem: RedditChildren): Boolean =
                oldItem.data.name == newItem.data.name
        }
    }
}

class RedditPaginationViewHolder(
    private val view: View
) : RecyclerView.ViewHolder(view) {
    val imagePreview: AppCompatImageView = view.imagePreview
    var model: RedditChildren? = null
        set(value) {
            field = value
            view.apply {
                tvRedditAuthor.text = value?.data?.author
                tvRedditCreatedUTC.text = value?.data?.createdUtc.toString()
                tvRedditScore.text = value?.data?.score.toString()
                tvRedditSelftext.text = value?.data?.selftext
                tvRedditTitle.text = value?.data?.title
            }
        }
}