package com.grappim.spacexapp.ui.history

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.grappim.spacexapp.R
import com.grappim.spacexapp.core.extensions.getErrorMessage
import com.grappim.spacexapp.core.extensions.getFragmentsComponent
import com.grappim.spacexapp.core.extensions.gone
import com.grappim.spacexapp.core.extensions.show
import com.grappim.spacexapp.core.extensions.showOrGone
import com.grappim.spacexapp.core.extensions.showSnackbar
import com.grappim.spacexapp.core.functional.Resource
import com.grappim.spacexapp.core.view.MarginItemDecorator
import com.grappim.spacexapp.api.model.history.HistoryModel
import com.grappim.spacexapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_history.pbFragmentHistory
import kotlinx.android.synthetic.main.fragment_history.rvFragmentHistory
import kotlinx.android.synthetic.main.fragment_history.srlHistory
import timber.log.Timber
import javax.inject.Inject

class HistoryFragment : BaseFragment(R.layout.fragment_history) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: HistoryViewModel by viewModels { viewModelFactory }

    private val historyAdaper: TimelineHistoryAdapter by lazy {
        TimelineHistoryAdapter {
            findNavController().navigate(HistoryFragmentDirections.nextFragment(it))
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val component = context.getFragmentsComponent()
        component.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Timber.d("HistoryFragment - onActivityCreated")
        super.onActivityCreated(savedInstanceState)

        viewModel.apply {
            allHistory.observe(viewLifecycleOwner, ::renderHistory)
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
        rvFragmentHistory.apply {
            addItemDecoration(MarginItemDecorator())
            adapter = historyAdaper
        }
    }

    private fun renderHistory(resource: Resource<List<HistoryModel>>) {
        pbFragmentHistory.showOrGone(resource is Resource.Loading)
        when (resource) {
            is Resource.Error -> {
                showError(resource.exception)
            }
            is Resource.Success -> {
                historyAdaper.loadItems(resource.data)
                rvFragmentHistory.scheduleLayoutAnimation()
            }
        }
    }

    override fun renderFailure(failureText: String) {
        rvFragmentHistory.showSnackbar(failureText)
        pbFragmentHistory.gone()
        srlHistory.isRefreshing = false
    }

    private fun showError(throwable: Throwable) {
        rvFragmentHistory.showSnackbar(requireContext().getErrorMessage(throwable))
    }
}
