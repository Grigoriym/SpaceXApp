package com.grappim.spacexapp.di.qualifiers

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class TwitterApiQualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class TwitterRetrofit