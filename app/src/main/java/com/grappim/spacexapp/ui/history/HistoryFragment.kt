package com.grappim.spacexapp.ui.history

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.grappim.spacexapp.R
import com.grappim.spacexapp.core.extensions.*
import com.grappim.spacexapp.model.history.HistoryModel
import com.grappim.spacexapp.core.view.MarginItemDecorator
import com.grappim.spacexapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_history.*
import timber.log.Timber
import javax.inject.Inject

class HistoryFragment : BaseFragment() {

  @Inject
  lateinit var viewModel: HistoryViewModel

  private lateinit var hAdapter: TimelineHistoryAdapter

  override fun onAttach(context: Context) {
    super.onAttach(context)
    getAppComponent().inject(this)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? =
    inflater.inflate(R.layout.fragment_history, container, false)

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    Timber.d("HistoryFragment - onActivityCreated")
    super.onActivityCreated(savedInstanceState)

    viewModel.apply {
      onObserve(allHistory, ::renderHistory)
      onFailure(failure, ::handleFailure)
    }

    bindAdapter()
    getData()

    srlHistory.setOnRefreshListener {
      getData()
      srlHistory.isRefreshing = false
    }
  }

  private fun getData() {
    pbFragmentHistory.show()
    viewModel.loadHistory()
  }

  private fun bindAdapter() {
    hAdapter = TimelineHistoryAdapter {
      findNavController().navigate(HistoryFragmentDirections.nextFragment(it))
    }
    rvFragmentHistory.apply {
      layoutManager = LinearLayoutManager(context)
      addItemDecoration(MarginItemDecorator())
      adapter = hAdapter
    }
  }

  private fun renderHistory(cores: List<HistoryModel>?) {
    hAdapter.loadItems(cores ?: listOf())
    pbFragmentHistory.gone()
    rvFragmentHistory.scheduleLayoutAnimation()
  }

  override fun renderFailure(failureText: String) {
    rvFragmentHistory.showSnackbar(failureText)
    pbFragmentHistory.gone()
    srlHistory.isRefreshing = false
  }
}
