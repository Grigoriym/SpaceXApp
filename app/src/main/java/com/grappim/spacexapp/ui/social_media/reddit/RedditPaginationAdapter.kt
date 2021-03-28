package com.grappim.spacexapp.ui.social_media.reddit

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.grappim.spacexapp.R
import com.grappim.spacexapp.api.model.reddit.RedditChildren
import com.grappim.spacexapp.core.extensions.gone
import com.grappim.spacexapp.core.extensions.inflate
import com.grappim.spacexapp.core.extensions.setSafeOnClickListener
import com.grappim.spacexapp.core.extensions.show
import kotlinx.android.synthetic.main.layout_reddit_item.view.imagePreview
import kotlinx.android.synthetic.main.layout_reddit_item.view.tvRedditAuthor
import kotlinx.android.synthetic.main.layout_reddit_item.view.tvRedditCreatedUTC
import kotlinx.android.synthetic.main.layout_reddit_item.view.tvRedditScore
import kotlinx.android.synthetic.main.layout_reddit_item.view.tvRedditSelftext
import kotlinx.android.synthetic.main.layout_reddit_item.view.tvRedditTitle

class RedditPaginationAdapter(
    private val onClick: (RedditChildren) -> Unit
) : PagingDataAdapter<RedditChildren, RedditPaginationViewHolder>(MY_DIFF_UTIL) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RedditPaginationViewHolder =
        RedditPaginationViewHolder(parent.inflate(R.layout.layout_reddit_item))

    override fun onBindViewHolder(holder: RedditPaginationViewHolder, position: Int) {
        holder.itemView.apply {
            val model = getItem(position) ?: return

            tvRedditAuthor.text = model.data.author
            tvRedditCreatedUTC.text = model.data.createdUtc.toString()
            tvRedditScore.text = model.data.score.toString()
            if (model.data.selftext.isNullOrBlank()) {
                tvRedditSelftext.gone()
            } else {
                tvRedditSelftext.show()
                tvRedditSelftext.text = model.data.selftext
            }
            tvRedditTitle.text = model.data.title

            imagePreview.load(model.data.preview?.images?.get(0)?.source?.url)

            setSafeOnClickListener { onClick(model) }
        }
    }

    companion object {
        val MY_DIFF_UTIL = object : DiffUtil.ItemCallback<RedditChildren>() {
            override fun areItemsTheSame(
                oldItem: RedditChildren,
                newItem: RedditChildren
            ): Boolean = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: RedditChildren,
                newItem: RedditChildren
            ): Boolean = oldItem.data.name == newItem.data.name
        }
    }
}

class RedditPaginationViewHolder(view: View) : RecyclerView.ViewHolder(view)