package com.grappim.spacexapp.ui.rockets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.grappim.spacexapp.R
import com.grappim.spacexapp.model.rocket.RocketModel
import com.grappim.spacexapp.recyclerview.MarginItemDecorator
import com.grappim.spacexapp.recyclerview.adapters.RocketsAdapter
import com.grappim.spacexapp.util.GlideApp
import com.grappim.spacexapp.util.gone
import com.grappim.spacexapp.util.show
import kotlinx.android.synthetic.main.fragment_get_rockets.*
import kotlinx.android.synthetic.main.layout_rocket_item.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class GetRocketsFragment : Fragment(), KodeinAware {

  override val kodein by kodein()

  private val viewModelFactory: RocketsSharedViewModelFactory by instance()

  private lateinit var rAdapter: RocketsAdapter

  private val observer = Observer<List<RocketModel>> {
    pbGetRockets.gone()
    rAdapter.loadItems(it)
    rvGetRockets.scheduleLayoutAnimation()
  }

  private val viewModel: RocketsSharedViewModel by lazy {
    ViewModelProviders
      .of(this, viewModelFactory)
      .get(RocketsSharedViewModel::class.java)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_get_rockets, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    pbGetRockets.show()
    viewModel.allRockets.observe(this, observer)
    bindAdapter()
    viewModel.getAllRockets()
    srlGetRockets.setOnRefreshListener {
      viewModel.getAllRockets()
      srlGetRockets.isRefreshing = false
    }
  }

  private fun bindAdapter() {
    rAdapter = RocketsAdapter(context) {
      val args = Bundle()
      args.putParcelable("model", it)
      findNavController().navigate(R.id.nextFragment, args)
    }
    rvGetRockets.apply {
      layoutManager = LinearLayoutManager(this.context)
      addItemDecoration(MarginItemDecorator())
      layoutAnimation = AnimationUtils
        .loadLayoutAnimation(context, R.anim.layout_animation_down_to_up)
      adapter = rAdapter
    }
  }

}
