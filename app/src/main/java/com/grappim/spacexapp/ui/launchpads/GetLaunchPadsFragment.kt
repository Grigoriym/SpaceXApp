package com.grappim.spacexapp.ui.launchpads

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.grappim.spacexapp.R
import com.grappim.spacexapp.model.launchpads.LaunchPadModel
import com.grappim.spacexapp.recyclerview.MarginItemDecorator
import com.grappim.spacexapp.recyclerview.adapters.LaunchPadsAdapter
import com.grappim.spacexapp.ui.SharedFragment
import com.grappim.spacexapp.util.gone
import com.grappim.spacexapp.util.show
import com.grappim.spacexapp.util.showSnackbar
import kotlinx.android.synthetic.main.fragment_get_launch_pads.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import retrofit2.Response

class GetLaunchPadsFragment : SharedFragment(), KodeinAware {

  override val kodein by kodein()

  private val viewModelFactory: LaunchPadViewModelFactory by instance()

  private val viewModel by viewModels<LaunchPadViewModel> { viewModelFactory }

  private lateinit var lAdapter: LaunchPadsAdapter
  private val observer = Observer<Response<List<LaunchPadModel>>> {
    pbGetLaunchPads.gone()
    if (it.isSuccessful) {
      it.body()?.let { items -> lAdapter.loadItems(items) }
    } else {
      srlGetLaunchPads.showSnackbar(getString(R.string.error_retrieving_data))
    }
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_get_launch_pads, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel.allLaunchPads.observe(this@GetLaunchPadsFragment, observer)

    bindAdapter()
    getData()
    srlGetLaunchPads.setOnRefreshListener {
      getData()
      srlGetLaunchPads.isRefreshing = false
    }
  }

  private fun getData() {
    pbGetLaunchPads.show()
    viewModel.getAllLaunchPads()
  }

  private fun bindAdapter() {
    lAdapter = LaunchPadsAdapter {
      findNavController().navigate(GetLaunchPadsFragmentDirections.nextFragment(it))
    }
    rvGetLaunchPads.apply {
      layoutManager = LinearLayoutManager(context)
      addItemDecoration(MarginItemDecorator())
      adapter = lAdapter
    }
  }

}
