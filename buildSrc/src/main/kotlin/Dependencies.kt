object Dependencies {

    val androidx = AndroidX
    val kotlin = Kotlin
    val lifecycle = LifeCycle
    val navigation = Navigation
    val hilt = Hilt
    val jsonParser = JsonParser
    val network = Network
    val imageLoader = ImageLoader
    val naver = Naver
    val test = Test

    object AndroidX {
        val core by lazy { "androidx.core:core-ktx:${Versions.core}" }
        val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.appCompact}" }
        val material by lazy { "com.google.android.material:material:${Versions.material}" }
        val constraint by lazy { "androidx.constraintlayout:constraintlayout:${Versions.constraint}" }
        val dataStore by lazy { "androidx.datastore:datastore-preferences:${Versions.datastore}" }
    }

    object Kotlin {
        val coroutine by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutine}" }
    }

    object LifeCycle {
        val viewModel by lazy { "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}" }
    }

    object Navigation {
        val fragment by lazy { "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}" }
        val ui by lazy { "androidx.navigation:navigation-ui-ktx:${Versions.navigation}" }
    }

    object Hilt {
        val android by lazy { "com.google.dagger:hilt-android:${Versions.hilt}" }
        val compiler by lazy { "com.google.dagger:hilt-compiler:${Versions.hilt}" }
    }

    object Network {
        val retrofit by lazy { "com.squareup.retrofit2:retrofit:${Versions.retrofit}" }
        val okhttpLoggingInterceptor by lazy { "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpLogging}" }
        val sandwich by lazy { "com.github.skydoves:sandwich:${Versions.sandwich}" }
    }

    object JsonParser {
        val gson by lazy { "com.google.code.gson:gson:${Versions.gson}" }
        val gsonConverter by lazy { "com.squareup.retrofit2:converter-gson:${Versions.retrofit}" }
    }

    object ImageLoader {
        val glide by lazy { "com.github.bumptech.glide:glide:${Versions.glide}" }
        val glideCompiler by lazy { "com.github.bumptech.glide:compiler:${Versions.glide}" }
    }

    object Naver {
        val map by lazy { "com.naver.maps:map-sdk:${Versions.map}" }
    }

    object Test {
        val jUnit by lazy { "junit:junit:${Versions.junit}" }
        val jUnitExt by lazy { "androidx.test.ext:junit:${Versions.junitExt}" }
        val expresso by lazy { "androidx.test.espresso:espresso-core:${Versions.espresso}" }
    }
}