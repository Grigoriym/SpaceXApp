package com.grappim.spacexapp.core.view

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.ProgressBar
import com.grappim.spacexapp.R

class LoaderDialog(
    context: Context,
    cancelable: Boolean = false
) {
    private var dialog: Dialog = Dialog(context, R.style.Dialog_Transparent)
    private var progressBar: ProgressBar

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_loader, null)
        progressBar = view.findViewById(R.id.progressBar)
        dialog.setContentView(view)
        dialog.setCancelable(cancelable)
    }

    fun show() {
        dialog.show()
    }

    fun hide() {
        dialog.dismiss()
    }

    fun showOrHide(show: Boolean) {
        when {
            show -> show()
            else -> hide()
        }
    }

}