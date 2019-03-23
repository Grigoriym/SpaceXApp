package com.grappim.spacexapp.ui.rockets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.grappim.spacexapp.R
import com.grappim.spacexapp.model.rocket.RocketModel
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class GetRocketsFragment : Fragment(), KodeinAware {

  override val kodein by kodein()

  private val viewModelFactory: RocketsSharedViewModelFactory by instance()

  private val observer = Observer<List<RocketModel>> {

  }

  private val viewModel: RocketsSharedViewModel by lazy {
    ViewModelProviders.of(this, viewModelFactory)
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
    activity?.title = "Get all rockets"

    viewModel.allRockets.observe(this, observer)
    bindAdapter()
    viewModel.getAllRockets()
  }

  private fun bindAdapter() {

  }

}
