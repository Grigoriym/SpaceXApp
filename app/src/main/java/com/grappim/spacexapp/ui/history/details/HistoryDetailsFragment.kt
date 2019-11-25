package com.grappim.spacexapp.ui.history.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.grappim.spacexapp.R
import com.grappim.spacexapp.ui.base.BaseFragment
import com.grappim.spacexapp.util.getDateForHistory
import kotlinx.android.synthetic.main.fragment_history_details.*
import timber.log.Timber

class HistoryDetailsFragment : BaseFragment() {

  private val args: HistoryDetailsFragmentArgs by navArgs()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? =
    inflater.inflate(R.layout.fragment_history_details, container, false)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    Timber.d("HistoryDetailsFragment - onViewCreated")

    args.historyModel.let {
      tvHistoryDetailsTitle.text = it.title
      tvHistoryDetailsDate.text = getDateForHistory(it.eventDateUtc ?: "")
      tvHistoryDetailsDetails.text = it.details
    }
  }
}
