plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.posomo.saltit.home"
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

    implementation(Dependencies.androidx.core)
    implementation(Dependencies.androidx.appCompat)
    implementation(Dependencies.androidx.material)
    implementation(Dependencies.androidx.constraint)

    testImplementation(Dependencies.test.jUnit)
    androidTestImplementation(Dependencies.test.jUnitExt)
    androidTestImplementation(Dependencies.test.expresso)
}