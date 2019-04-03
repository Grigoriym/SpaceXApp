package com.grappim.spacexapp.recyclerview.adapters

import android.content.Context
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.grappim.spacexapp.R
import com.grappim.spacexapp.model.rocket.RocketModel
import com.grappim.spacexapp.recyclerview.viewholders.RocketsViewHolder
import com.grappim.spacexapp.util.GlideApp
import com.grappim.spacexapp.util.inflateLayout

class RocketsAdapter(
  private val context: Context?,
  val onClick: (RocketModel) -> Unit
) : RecyclerView.Adapter<RocketsViewHolder>() {

  var items: List<RocketModel> = emptyList()

  private val animationUp = AnimationUtils.loadAnimation(context, R.anim.slide_up)
  private val animationDown = AnimationUtils.loadAnimation(context, R.anim.slide_down)

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
    GlideApp.with(context!!)
      .load("https://www.spaceflightinsider.com/wp-content/uploads/hangar/header/falcon-9.jpg")
      .into(holder.ivGetRockets)
    holder.apply {
      rocket = items[position]
      btnRocketItem.setOnClickListener {
        onClick(items[position])
      }
      itemView.setOnClickListener {
        if (holder.tvRocketDescription.isShown) {
          holder.tvRocketDescription.startAnimation(animationUp)
          val countDownTimeStatic = object : CountDownTimer(200, 16) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
              holder.tvRocketDescription.visibility = View.GONE
            }
          }
          countDownTimeStatic.start()
        } else {
          holder.tvRocketDescription.visibility = View.VISIBLE
          holder.tvRocketDescription.startAnimation(animationDown)
        }
      }
    }
  }

  fun loadItems(newItems: List<RocketModel>) {
    items = newItems
    notifyDataSetChanged()
  }

}