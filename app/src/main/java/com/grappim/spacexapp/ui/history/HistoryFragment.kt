package com.grappim.spacexapp.ui.history

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
import com.grappim.spacexapp.model.history.HistoryModel
import com.grappim.spacexapp.recyclerview.MarginItemDecorator
import com.grappim.spacexapp.recyclerview.adapters.TimelineHistoryAdapter
import com.grappim.spacexapp.util.PARCELABLE_HISTORY_MODEL
import com.grappim.spacexapp.util.gone
import com.grappim.spacexapp.util.show
import kotlinx.android.synthetic.main.fragment_history.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class HistoryFragment : Fragment(), KodeinAware {

  override val kodein by kodein()

  private lateinit var hAdapter: TimelineHistoryAdapter

  private val viewModelFactory: HistoryViewModelFactory by instance()

  private val observer = Observer<List<HistoryModel>> {
    pbFragmentHistory.gone()
    hAdapter.loadItems(it)
  }

  private val viewModel: HistoryViewModel by lazy {
    ViewModelProviders
      .of(this, viewModelFactory)
      .get(HistoryViewModel::class.java)
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
      val args = Bundle()
      args.putParcelable(PARCELABLE_HISTORY_MODEL, it)
      findNavController().navigate(R.id.nextFragment, args)
    }
    rvFragmentHistory.apply {
      layoutManager = LinearLayoutManager(context)
      addItemDecoration(MarginItemDecorator())
      adapter = hAdapter
    }
  }

}
