package com.grappim.spacexapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.grappim.spacexapp.util.Failure

abstract class BaseViewModel : ViewModel() {

  private val _failure = MutableLiveData<Failure>()
  val failure: LiveData<Failure>
    get() = _failure

  protected fun handleFailure(failure: Failure) {
    this._failure.value = failure
  }
}