package com.grappim.spacexapp.recyclerview.adapters

import android.content.Context
import android.os.CountDownTimer
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.grappim.spacexapp.R
import com.grappim.spacexapp.core.extensions.gone
import com.grappim.spacexapp.core.extensions.inflateLayout
import com.grappim.spacexapp.core.extensions.setSafeOnClickListener
import com.grappim.spacexapp.core.extensions.show
import com.grappim.spacexapp.model.rocket.RocketModel
import com.grappim.spacexapp.recyclerview.viewholders.RocketsViewHolder
import com.grappim.spacexapp.util.*

class RocketsAdapter(
  context: Context?,
  private inline val onClick: (RocketModel) -> Unit
) : RecyclerView.Adapter<RocketsViewHolder>() {

  private var items: List<RocketModel> = emptyList()

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): RocketsViewHolder =
    RocketsViewHolder(
      parent
        .context
        .inflateLayout(R.layout.layout_rocket_item, parent)
    )

  override fun getItemCount(): Int = items.size

  override fun onBindViewHolder(
    holder: RocketsViewHolder,
    position: Int
  ) {
    holder.apply {
      rocket = items[position]
      btnRocketItem.setSafeOnClickListener {
        onClick(items[position])
      }

      GlideApp.with(btnRocketItem.context)
        .load(items[position].flickrImages?.random())
        .transition(DrawableTransitionOptions.withCrossFade())
        .centerCrop()
        .apply(RequestOptions().placeholder(R.drawable.glide_placeholder).centerCrop())
        .into(ivGetRockets)

      itemView.setOnClickListener {
        if (tvRocketDescription.isShown) {
          tvRocketDescription.startAnimation(
            AnimationUtils.loadAnimation(
              itemView.context,
              R.anim.slide_up
            )
          )
          val countDownTimeStatic = object : CountDownTimer(200, 16) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
              tvRocketDescription.gone()
            }
          }
          countDownTimeStatic.start()
        } else {
          tvRocketDescription.apply {
            show()
            startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_down))
          }
        }
      }
    }
  }

  fun loadItems(newItems: List<RocketModel>) {
    items = newItems
    notifyDataSetChanged()
  }

}