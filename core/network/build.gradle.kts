plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.posomo.saltit.network"
    compileSdk = Configuration.compileSdk

    defaultConfig {
        minSdk = Configuration.minSdk
        targetSdk = Configuration.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {

    implementation(Dependencies.retrofit)
    implementation(Dependencies.jsonParser.gsonConverter)
    implementation(Dependencies.okHttp)
    implementation(Dependencies.okHttp.loggingInterceptor)

    implementation(Dependencies.hilt.android)
    kapt(Dependencies.hilt.compiler)

    testImplementation(Dependencies.test.jUnit)
    androidTestImplementation(Dependencies.test.jUnitExt)
    androidTestImplementation(Dependencies.test.expresso)
}