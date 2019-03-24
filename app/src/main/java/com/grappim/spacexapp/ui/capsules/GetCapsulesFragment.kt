package com.grappim.spacexapp.ui.capsules

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.grappim.spacexapp.R
import com.grappim.spacexapp.model.capsule.CapsuleModel
import com.grappim.spacexapp.recyclerview.MarginItemDecorator
import com.grappim.spacexapp.recyclerview.adapters.CapsulesAdapter
import com.grappim.spacexapp.util.FieldConstants
import kotlinx.android.synthetic.main.fragment_get_capsules.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class GetCapsulesFragment : Fragment(), KodeinAware {

  override val kodein by kodein()

  private lateinit var cAdapter: CapsulesAdapter

  private val viewModelFactory: CapsuleSharedViewModelFactory by instance()

  private val observer = Observer<List<CapsuleModel>> {
    cAdapter.loadItems(it)
  }

  private var args: Int? = null

  private val viewModel: CapsuleSharedViewModel by lazy {
    ViewModelProviders.of(this, viewModelFactory).get(CapsuleSharedViewModel::class.java)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    args = arguments?.getInt(FieldConstants.CAPSULES_ARGS)
    return inflater.inflate(R.layout.fragment_get_capsules, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    activity?.title = "Get all Capsules"
    viewModel.allCapsules.observe(this, observer)
    viewModel.upcomingCapsules.observe(this, observer)
    viewModel.pastCapsules.observe(this, observer)
    bindAdapter()
    getData()
    srlGetCapsules.setOnRefreshListener {
      getData()
      srlGetCapsules.isRefreshing = false
    }
  }

  private fun getData() {
    when (args) {
      1 -> viewModel.getAllCapsules()
      2 -> viewModel.getUpcomingCapsules()
      3 -> viewModel.getPastCapsules()
    }
  }

  private fun bindAdapter() {
    cAdapter = CapsulesAdapter {
      val args = Bundle()
      args.putParcelable("model", it)
      findNavController().navigate(R.id.nextFragment, args)
    }
    rvGetAllCapsules.apply {
      layoutManager = LinearLayoutManager(this.context)
      addItemDecoration(MarginItemDecorator())
      adapter = cAdapter
    }
  }
}
