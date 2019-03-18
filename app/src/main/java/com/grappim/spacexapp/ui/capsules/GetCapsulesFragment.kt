package com.grappim.spacexapp.ui.capsules

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.grappim.spacexapp.R
import com.grappim.spacexapp.model.capsule.CapsuleModel
import com.grappim.spacexapp.network.API
import com.grappim.spacexapp.network.interceptors.ConnectivityInterceptorImpl
import com.grappim.spacexapp.recyclerview.MarginItemDecorator
import com.grappim.spacexapp.recyclerview.adapters.CapsulesAdapter
import com.grappim.spacexapp.util.enqueue
import kotlinx.android.synthetic.main.fragment_get_capsules.*

class GetCapsulesFragment : Fragment() {

  private lateinit var cAdapter: CapsulesAdapter

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_get_capsules, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    activity?.title = "Get all Capsules"

    bindAdapter()
    apiGetCapsules()
  }

  private fun apiGetCapsules() {
    API(ConnectivityInterceptorImpl(this.context)).getCapsules().enqueue {
      onResponse = { response ->
        if (response.body() != null) {
          cAdapter.loadItems(response.body() as List<CapsuleModel>)
        }
      }
    }
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
