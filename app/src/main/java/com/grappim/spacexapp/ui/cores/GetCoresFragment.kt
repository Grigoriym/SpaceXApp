package com.grappim.spacexapp.ui.cores

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
import com.google.android.material.snackbar.Snackbar
import com.grappim.spacexapp.R
import com.grappim.spacexapp.model.cores.CoreModel
import com.grappim.spacexapp.recyclerview.MarginItemDecorator
import com.grappim.spacexapp.recyclerview.adapters.CoresAdapter
import com.grappim.spacexapp.util.CORES_ARGS
import com.grappim.spacexapp.util.gone
import com.grappim.spacexapp.util.show
import kotlinx.android.synthetic.main.fragment_get_cores.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class GetCoresFragment : Fragment(), KodeinAware {

  override val kodein by kodein()

  private lateinit var coreAdapter: CoresAdapter

  private val observer = Observer<List<CoreModel>> {
    pbGetCores.gone()
    coreAdapter.loadItems(it)
    rvGetCores.scheduleLayoutAnimation()
  }

  private var args: Int? = null

  private val viewModelFactory: CoreSharedViewModelFactory by instance()

  private val viewModel: CoresSharedViewModel by lazy {
    ViewModelProviders
      .of(this, viewModelFactory)
      .get(CoresSharedViewModel::class.java)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    args = arguments?.getInt(CORES_ARGS)
    return inflater.inflate(R.layout.fragment_get_cores, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    pbGetCores.show()

    viewModel.apply {
      allCores.observe(this@GetCoresFragment, observer)
      upcomingCores.observe(this@GetCoresFragment, observer)
      pastCores.observe(this@GetCoresFragment, observer)
    }

    bindAdapter()
    getData()
    srlGetCores.setOnRefreshListener {
      getData()
      srlGetCores.isRefreshing = false
    }
  }

  private fun getData() {
    when (args) {
      1 -> viewModel.getAllCapsules()
      2 -> viewModel.getPastCores()
      3 -> viewModel.getUpcomingCores()
      else -> {
        Snackbar.make(srlGetCores, "Cannot retrieve data", Snackbar.LENGTH_LONG).show()
        findNavController().popBackStack()
      }
    }
  }

  private fun bindAdapter() {
    coreAdapter = CoresAdapter {}
    rvGetCores.apply {
      layoutManager = LinearLayoutManager(context)
      addItemDecoration(MarginItemDecorator())
      layoutAnimation = AnimationUtils
        .loadLayoutAnimation(context, R.anim.layout_animation_down_to_up)
      adapter = coreAdapter
    }
  }
}
