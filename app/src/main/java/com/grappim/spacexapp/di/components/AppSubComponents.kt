package com.grappim.spacexapp.di.components

import dagger.Module

@Module(
    subcomponents = [
        FragmentsComponent::class,
        CapsuleComponent::class
    ]
)
class AppSubComponents