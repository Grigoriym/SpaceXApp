package com.grappim.spacexapp.di.components

import com.grappim.spacexapp.di.scopes.ActivityScope
import dagger.Component

@ActivityScope
@Component(
    dependencies = [
        AppComponent::class
    ]
)
interface ActivityComponent