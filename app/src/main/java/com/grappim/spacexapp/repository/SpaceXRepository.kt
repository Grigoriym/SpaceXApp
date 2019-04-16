package com.grappim.spacexapp.repository

import androidx.lifecycle.LiveData
import com.grappim.spacexapp.model.capsule.CapsuleModel

interface SpaceXRepository {

  fun getCapsules():LiveData<List<CapsuleModel>>
}