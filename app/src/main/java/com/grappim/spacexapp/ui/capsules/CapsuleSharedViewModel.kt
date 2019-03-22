package com.grappim.spacexapp.ui.capsules

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.grappim.spacexapp.model.capsule.CapsuleModel
import com.grappim.spacexapp.network.API
import kotlinx.coroutines.*

class CapsuleSharedViewModel(
  private val api: API
) : ViewModel() {

  val allCapsules = MutableLiveData<List<CapsuleModel>>()

  fun getAllCapsules() {
    CoroutineScope((Dispatchers.IO)).launch {
      val request = api.getCapsules()
      withContext(Dispatchers.Main){
        val response = request.await()
        if (response.isSuccessful) {
          response.body()?.let { allCapsules.value = it }
        }
      }
    }
  }

}