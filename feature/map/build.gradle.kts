import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

val apikeyProperties = Properties().apply {
    load(FileInputStream(rootProject.file("apikey.properties")))
}

android {

    namespace = "com.posomo.saltit.map"
    compileSdk = Configuration.compileSdk

    defaultConfig {
        minSdk = Configuration.minSdk
        targetSdk = Configuration.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        manifestPlaceholders["NAVER_MAP_API"] = apikeyProperties["mapClientId"] as String
    }

    buildTypes {
        debug {

        }

        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro")
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

    implementation(project(":core:domain"))
    implementation(project(":core:common-ui"))

    implementation(Dependencies.androidx.core)
    implementation(Dependencies.androidx.appCompat)
    implementation(Dependencies.androidx.material)
    implementation(Dependencies.androidx.constraint)

    implementation(Dependencies.navigation.ui)
    implementation(Dependencies.navigation.fragment)

    implementation(Dependencies.hilt.android)
    kapt(Dependencies.hilt.compiler)

    implementation(Dependencies.naver.map)

    testImplementation(Dependencies.test.jUnit)
    androidTestImplementation(Dependencies.test.jUnitExt)
    androidTestImplementation(Dependencies.test.expresso)
}