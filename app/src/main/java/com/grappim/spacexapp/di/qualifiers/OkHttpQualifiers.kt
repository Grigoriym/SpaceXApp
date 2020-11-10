package com.grappim.spacexapp.di.qualifiers

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class OauthClientQualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class GeneralClientQualifier