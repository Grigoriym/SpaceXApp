package com.grappim.spacexapp.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.grappim.spacexapp.R
import com.grappim.spacexapp.model.history.HistoryModel
import com.grappim.spacexapp.recyclerview.MarginItemDecorator
import com.grappim.spacexapp.recyclerview.adapters.TimelineHistoryAdapter
import com.grappim.spacexapp.ui.SharedFragment
import com.grappim.spacexapp.util.gone
import com.grappim.spacexapp.util.show
import com.grappim.spacexapp.util.showSnackbar
import kotlinx.android.synthetic.main.fragment_history.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import retrofit2.Response

class HistoryFragment : SharedFragment(), KodeinAware {

  override val kodein by kodein()

  private lateinit var hAdapter: TimelineHistoryAdapter

  private val viewModelFactory: HistoryViewModelFactory by instance()

  private val viewModel by viewModels<HistoryViewModel> { viewModelFactory }

  private val observer = Observer<Response<List<HistoryModel>>> {
    pbFragmentHistory.gone()
    if (it.isSuccessful) {
      it.body()?.let { items -> hAdapter.loadItems(items) }
    } else {
      rvFragmentHistory.showSnackbar(getString(R.string.error_retrieving_data))
    }
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_history, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    pbFragmentHistory.show()

    viewModel.apply {
      allHistory.observe(this@HistoryFragment, observer)
    }

    bindAdapter()
    getData()
  }

  private fun getData() {
    viewModel.getAllHistory()
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

  override fun renderFailure(failureText: String) {
  }
}
