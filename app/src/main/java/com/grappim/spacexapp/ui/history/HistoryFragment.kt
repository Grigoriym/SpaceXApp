package com.grappim.spacexapp.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.grappim.spacexapp.R
import com.grappim.spacexapp.model.history.HistoryModel
import com.grappim.spacexapp.recyclerview.MarginItemDecorator
import com.grappim.spacexapp.recyclerview.adapters.TimelineHistoryAdapter
import com.grappim.spacexapp.ui.base.BaseFragment
import com.grappim.spacexapp.util.*
import kotlinx.android.synthetic.main.fragment_history.*
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber

class HistoryFragment : BaseFragment(), KoinComponent {

  private lateinit var hAdapter: TimelineHistoryAdapter
  private val viewModelFactory:HistoryViewModelFactory by inject()
  private val viewModel by viewModels<HistoryViewModel> { viewModelFactory }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_history, container, false)
  }

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
    hAdapter.loadItems(cores!!)//todo
    pbFragmentHistory.gone()
    rvFragmentHistory.scheduleLayoutAnimation()
  }

  override fun renderFailure(failureText: String) {
    rvFragmentHistory.showSnackbar(failureText)
    pbFragmentHistory.gone()
    srlHistory.isRefreshing = false
  }
}
