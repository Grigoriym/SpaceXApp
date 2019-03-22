package com.grappim.spacexapp.ui.capsules

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.grappim.spacexapp.R
import com.grappim.spacexapp.network.API
import com.grappim.spacexapp.network.interceptors.ConnectivityInterceptorImpl
import com.grappim.spacexapp.recyclerview.MarginItemDecorator
import com.grappim.spacexapp.recyclerview.adapters.CapsulesAdapter
import com.grappim.spacexapp.ui.BaseViewModelFactory
import kotlinx.android.synthetic.main.fragment_get_capsules.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import timber.log.Timber

class GetCapsulesFragment : Fragment(), KodeinAware {

  override val kodein by kodein()

  private lateinit var cAdapter: CapsulesAdapter

  private val viewModelFactory: CapsuleSahredViewModelFactory by instance()

  private val viewModel: CapsuleSharedViewModel by lazy {
    ViewModelProviders.of(this, viewModelFactory).get(CapsuleSharedViewModel::class.java)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_get_capsules, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    activity?.title = "Get all Capsules"

    viewModel.allCapsules.observe(this, Observer {
      cAdapter.loadItems(it)
    })

    bindAdapter()
    viewModel.getAllCapsules()
  }

  private fun bindAdapter() {
    cAdapter = CapsulesAdapter { }
    rvGetAllCapsules.apply {
      layoutManager = LinearLayoutManager(this.context)
      addItemDecoration(MarginItemDecorator())
      adapter = cAdapter
    }
  }

}
