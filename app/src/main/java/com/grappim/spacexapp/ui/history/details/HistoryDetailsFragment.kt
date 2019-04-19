package com.grappim.spacexapp.ui.history.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.grappim.spacexapp.R
import com.grappim.spacexapp.model.history.HistoryModel
import com.grappim.spacexapp.util.PARCELABLE_HISTORY_MODEL
import kotlinx.android.synthetic.main.fragment_history_details.*
import timber.log.Timber

class HistoryDetailsFragment : Fragment() {

  private var args: HistoryModel? = null

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    args = arguments?.getParcelable(PARCELABLE_HISTORY_MODEL)
    return inflater.inflate(R.layout.fragment_history_details, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    Timber.d("HistoryDetailsFragment - onViewCreated")

    args?.let {
      tvHistoryDetailsTitle.text = it.title
      tvHistoryDetailsDate.text = it.eventDateUtc
      tvHistoryDetailsDetails.text = it.details
    }
  }

}
