package com.grappim.spacexapp.pagination

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.grappim.spacexapp.R
import com.grappim.spacexapp.model.twitter.UserTimelineModel
import com.grappim.spacexapp.recyclerview.MarginItemDecorator
import com.grappim.spacexapp.recyclerview.TwitterItemImageAdapter
import com.grappim.spacexapp.util.*
import kotlinx.android.synthetic.main.layout_twitter_item.view.*
import timber.log.Timber

class TwitterPaginationAdapter(
  val onClick: (UserTimelineModel) -> Unit,
  val onImageClickS: (String) -> Unit
) : PagedListAdapter<UserTimelineModel,
    TwitterPaginationViewHolder>(MY_DIFF_UTIL) {

  companion object {
    val MY_DIFF_UTIL = object : DiffUtil.ItemCallback<UserTimelineModel>() {
      override fun areItemsTheSame(
        oldItem: UserTimelineModel,
        newItem: UserTimelineModel
      ): Boolean =
        oldItem == newItem

      override fun areContentsTheSame(
        oldItem: UserTimelineModel,
        newItem: UserTimelineModel
      ): Boolean =
        oldItem.id == newItem.id
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TwitterPaginationViewHolder =
    TwitterPaginationViewHolder(
      parent
        .context
        .inflateLayout(R.layout.layout_twitter_item, parent)
    )

  override fun onBindViewHolder(holder: TwitterPaginationViewHolder, position: Int) {
    Timber.d("TwitterPaginationAdapter - onBindViewHolder")
    holder.apply {
      userTimelineModel = getItem(position)
      onImageClick = {
        onImageClickS(it)
      }
      itemView.setOnClickListener { onClick(getItem(position)!!) }
      GlideApp.with(profileImage.context)
        .load(getItem(position)?.user?.profileImageUrlHttps)
        .transition(DrawableTransitionOptions.withCrossFade())
        .centerCrop()
        .apply(RequestOptions().placeholder(R.drawable.glide_placeholder).centerCrop())
        .into(profileImage)
    }
  }
}

class TwitterPaginationViewHolder(
  private val view: View
) : RecyclerView.ViewHolder(view) {
  var onImageClick: (String) -> Unit = {}
  val profileImage: ImageView = view.ivTwitterItemProfileImage
  val rv: RecyclerView = view.rlTwitterItemMedia
  var userTimelineModel: UserTimelineModel? = null
    set(value) {
      field = value
      view.apply {
        setInnerRv(this, value)
        tvTwitterItemCreatedAt.text = getTwitterDate(value?.createdAt)
        tvTwitterItemScreenName.text = "@${value?.user?.screenName}"
        tvTwitterItemText.text = value?.fullText
        tvTwitterName.text = value?.user?.name
      }
    }

  private fun setInnerRv(view: View, value: UserTimelineModel?) {
    if (value?.extendedEntities?.media?.get(0) != null) {
      rv.show()
      val mediaList = value.extendedEntities.media
      val listOfStrings = mutableListOf<String>()

      for (i in mediaList) {
        listOfStrings.add(i?.mediaUrlHttps ?: "")
      }
      when (listOfStrings.size) {
        1 -> {
          rv.layoutManager = GridLayoutManager(view.context, 1)
        }
        2 -> {
          val glm = GridLayoutManager(
            view.context,
            2
          )
          rv.layoutManager = glm
          rv.addItemDecoration(MarginItemDecorator(isHorizontal = true))
        }
        3 -> {
          val glm = GridLayoutManager(
            view.context,
            2
          )
          glm.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
              return when (position) {
                0 -> {
                  2
                }
                1 -> {
                  1
                }
                else -> {
                  1
                }
              }
            }
          }
          rv.layoutManager = glm
          rv.addItemDecoration(
            MarginItemDecorator(
              isGridLayout = true,
              spanCount = 2,
              spacing = 8.px,
              includeEdge = false
            )
          )
        }
        4 -> {
          val glm = GridLayoutManager(
            view.context,
            2
          )
          rv.layoutManager = glm
          rv.addItemDecoration(
            MarginItemDecorator(
              isGridLayout = true,
              spanCount = 2,
              spacing = 8.px,
              includeEdge = false
            )
          )
        }
        else -> {
          rv.layoutManager = LinearLayoutManager(view.context)
        }
      }

      val iAdapter = TwitterItemImageAdapter(onImageClick = {
        onImageClick(it)
      })
      rv.adapter = iAdapter

      iAdapter.loadItems(listOfStrings)
    } else {
      rv.gone()
    }
  }
}