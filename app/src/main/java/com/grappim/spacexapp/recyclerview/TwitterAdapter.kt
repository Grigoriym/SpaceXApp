package com.grappim.spacexapp.recyclerview

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.grappim.spacexapp.R
import com.grappim.spacexapp.model.twitter.UserTimelineModel
import com.grappim.spacexapp.util.GlideApp
import com.grappim.spacexapp.util.inflateLayout
import kotlinx.android.synthetic.main.layout_twitter_item.view.*

class TwitterAdapter(
  val onClick: (UserTimelineModel) -> Unit
) : RecyclerView.Adapter<TwitterViewHolder>() {

  private var items: List<UserTimelineModel> = emptyList()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TwitterViewHolder =
    TwitterViewHolder(
      parent
        .context
        .inflateLayout(R.layout.layout_twitter_item, parent)
    )

  override fun getItemCount(): Int = items.size

  override fun onBindViewHolder(holder: TwitterViewHolder, position: Int) {
    holder.apply {
      userTimelineModel = items[position]
      itemView.setOnClickListener { onClick(items[position]) }
      GlideApp.with(profileImage.context)
        .load(items[position].user.profile_image_url_https)
        .transition(DrawableTransitionOptions.withCrossFade())
        .centerCrop()
        .apply(RequestOptions().placeholder(R.drawable.glide_placeholder).centerCrop())
        .into(profileImage)
    }
  }

  fun loadItems(newItems: List<UserTimelineModel>) {
    items = newItems
    notifyDataSetChanged()
  }
}

class TwitterViewHolder(
  private val view: View
) : RecyclerView.ViewHolder(view) {
  val profileImage: ImageView = view.ivTwitterItemProfileImage
  var userTimelineModel: UserTimelineModel? = null
    set(value) {
      field = value
      view.apply {
        tvTwitterItemCreatedAt.text = value?.created_at
        tvTwitterItemScreenName.text = "@${value?.user?.screen_name}"
        tvTwitterItemText.text = value?.text
      }
    }
}