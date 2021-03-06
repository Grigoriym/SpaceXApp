package com.grappim.spacexapp.ui.history.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.grappim.spacexapp.R
import com.grappim.spacexapp.core.extensions.getOffsetDateTime
import com.grappim.spacexapp.core.utils.DateTimeUtils
import com.grappim.spacexapp.ui.base.BaseFragment
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
      tvHistoryDetailsDate.text =
        DateTimeUtils.getDateTimeFormatter().format(it.eventDateUtc?.getOffsetDateTime())
      tvHistoryDetailsDetails.text = it.details
    }
  }
}
