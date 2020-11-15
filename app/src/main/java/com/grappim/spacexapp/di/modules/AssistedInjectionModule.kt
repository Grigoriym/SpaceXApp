package com.grappim.spacexapp.di.modules

import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Module

@AssistedModule
@Module(includes = [AssistedInject_AssistedInjectionModule::class])
class AssistedInjectionModule