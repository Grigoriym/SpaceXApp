package com.grappim.spacexapp.di.components

import com.grappim.spacexapp.di.scopes.FragmentScope
import dagger.Subcomponent

@FragmentScope
@Subcomponent
interface FragmentsComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): FragmentsComponent
    }

}