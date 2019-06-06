import com.android.build.gradle.api.ApplicationVariant
import com.android.build.gradle.api.BaseVariantOutput
import com.android.build.gradle.internal.api.BaseVariantOutputImpl

plugins {
  id("com.android.application")
  kotlin("android")
  kotlin("android.extensions")
  kotlin("kapt")
  id("androidx.navigation.safeargs.kotlin")
}

android {
  compileSdkVersion(28)

  defaultConfig {
    applicationId = "com.grappim.spacexapp"
    minSdkVersion(19)
    targetSdkVersion(28)
    versionCode = 1
    versionName = "1.9.2"
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    getByName("release") {
      isMinifyEnabled = true
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
    getByName("debug") {
      multiDexEnabled = true
      buildConfigField("String", "ApiKey", extra.get("twitter_api_key").toString())
      buildConfigField("String", "SecretApiKey", extra.get("twitter_api_secret_key").toString())
      buildConfigField("String", "AccessToken", extra.get("twitter_api_access_token").toString())
      buildConfigField(
        "String",
        "AccessTokenSecret",
        extra.get("twitter_api_access_token_secret").toString()
      )
    }
  }

  applicationVariants.all(object : Action<ApplicationVariant> {
    override fun execute(variant: ApplicationVariant) {
      variant.outputs.all(object : Action<BaseVariantOutput> {
        override fun execute(output: BaseVariantOutput) {
          val outputImpl = output as BaseVariantOutputImpl
          val fileName = output.outputFileName
            .replace(
              "-release",
              "-release-v${defaultConfig.versionName}-vc${defaultConfig.versionCode}"
            )
            .replace(
              "-debug",
              "-debug-v${defaultConfig.versionName}-vc${defaultConfig.versionCode}"
            )
          outputImpl.outputFileName = fileName
        }
      })
    }
  })

  androidExtensions {
    isExperimental = true
  }

  buildToolsVersion = "28.0.3"

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
}

dependencies {
  implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

  implementation("androidx.multidex:multidex:2.0.1")

  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.31")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.1.1")

  //    Navigation
  implementation("androidx.navigation:navigation-fragment-ktx:2.1.0-alpha05")
  implementation("androidx.navigation:navigation-ui-ktx:2.1.0-alpha05")

  implementation("androidx.appcompat:appcompat:1.1.0-alpha05")
  implementation("androidx.core:core-ktx:1.2.0-alpha01")
  implementation("androidx.constraintlayout:constraintlayout:2.0.0-beta1")
  implementation("androidx.paging:paging-runtime-ktx:2.1.0")

  implementation("com.google.code.gson:gson:2.8.5")

  //    Lifecycle
  implementation("androidx.lifecycle:lifecycle-extensions:2.2.0-alpha01")
  implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0-alpha01")

  implementation("com.google.android.material:material:1.1.0-alpha07")

  //    Retrofit, okHttp-logging, retrofit-coroutines
  implementation("com.squareup.retrofit2:retrofit:2.6.0")
  implementation("com.squareup.retrofit2:converter-gson:2.5.0")
  implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")
  implementation("com.squareup.okhttp3:logging-interceptor:3.12.1")

  implementation("com.jakewharton.timber:timber:4.7.1")

  implementation("com.github.vipulasri:timelineview:1.1.0")

  //    Glide
  implementation("com.github.bumptech.glide:glide:4.9.0")
  kapt("com.github.bumptech.glide:compiler:4.9.0")

  //    Kodein
  implementation("org.kodein.di:kodein-di-generic-jvm:6.1.0")
  implementation("org.kodein.di:kodein-di-framework-android-x:6.1.0")

  implementation("com.google.android.gms:play-services-auth:16.0.1")

  implementation("com.jakewharton.threetenabp:threetenabp:1.2.0")

  testImplementation("junit:junit:4.13-beta-3")
  androidTestImplementation("androidx.test:runner:1.2.0")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
}
