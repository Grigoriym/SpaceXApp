buildscript {
  val kotlinVersion = "1.3.41"
  repositories {
    google()
    jcenter()
  }
  dependencies {
    classpath("com.android.tools.build:gradle:3.4.2")
    classpath(kotlin("gradle-plugin", kotlinVersion))
    classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.2.0-alpha01")
  }
}

allprojects {
  repositories {
    google()
    jcenter()
  }
}

tasks {
  val clean by registering(Delete::class) {
    delete(buildDir)
  }
}