package com.grappim.spacexapp.di.components

import com.grappim.spacexapp.di.scopes.CapsuleScope
import com.grappim.spacexapp.ui.capsules.GetCapsulesFragment
import dagger.Subcomponent

@CapsuleScope
@Subcomponent
interface CapsuleComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): CapsuleComponent
    }

    fun inject(getCapsulesFragment: GetCapsulesFragment)

}