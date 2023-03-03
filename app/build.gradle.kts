plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.posomo.saltit"
    compileSdk = Configuration.compileSdk

    defaultConfig {
        applicationId = "com.posomo.saltit"
        minSdk = Configuration.minSdk
        targetSdk = Configuration.targetSdk
        versionCode = Configuration.versionCode
        versionName = Configuration.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
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

    implementation(project(":feature:home"))
    implementation(project(":feature:login"))
    implementation(project(":core:common-ui"))

    implementation(Dependencies.androidx.core)
    implementation(Dependencies.androidx.appCompat)
    implementation(Dependencies.androidx.material)
    implementation(Dependencies.androidx.constraint)
    implementation(Dependencies.androidx.splashScreen)

    implementation(Dependencies.navigation.ui)
    implementation(Dependencies.navigation.fragment)

    implementation(Dependencies.hilt.android)
	kapt(Dependencies.hilt.compiler)

    testImplementation(Dependencies.test.jUnit)
    androidTestImplementation(Dependencies.test.jUnitExt)
    androidTestImplementation(Dependencies.test.expresso)
}