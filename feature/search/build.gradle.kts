import java.io.FileInputStream
import java.util.*

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

val properties = Properties().apply {
    load(FileInputStream(rootProject.file("apikey.properties")))
}

android {
    namespace = "com.posomo.saltit.search"
    compileSdk = Configuration.compileSdk

    defaultConfig {
        minSdk = Configuration.minSdk
        targetSdk = Configuration.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            manifestPlaceholders["NAVER_CLIENT_ID"] = properties["naverClientId"] as String
        }
        release {
            isMinifyEnabled = false
            manifestPlaceholders["NAVER_CLIENT_ID"] = properties["naverClientId"] as String
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = Configuration.javaVersion
        targetCompatibility = Configuration.javaVersion
    }
    kotlinOptions {
        jvmTarget = Configuration.jvmTarget
    }
    dataBinding {
        enable = true
    }
}

dependencies {

    implementation(project(":core:common-ui"))
    implementation(project(":core:model"))

    implementation(Dependencies.androidx.core)
    implementation(Dependencies.androidx.appCompat)
    implementation(Dependencies.androidx.material)
    implementation(Dependencies.androidx.constraint)

    implementation(Dependencies.navigation.ui)
    implementation(Dependencies.navigation.fragment)

    implementation(Dependencies.naver.map)
    implementation(Dependencies.naver.gmsLocation)

    implementation(Dependencies.hilt.android)
    kapt(Dependencies.hilt.compiler)

    testImplementation(Dependencies.test.jUnit)
    androidTestImplementation(Dependencies.test.jUnitExt)
    androidTestImplementation(Dependencies.test.expresso)
}