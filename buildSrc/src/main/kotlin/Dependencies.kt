object Dependencies {

    val androidx = AndroidX
    val kotlin = Kotlin
    val lifecycle = LifeCycle
    val navigation = Navigation
    val hilt = Hilt
    val retrofit = Retrofit()
    val okHttp = OkHttp()
    val kotlinx = KotlinX
    
    val jsonParser = JsonParser
    val network = Network
    val imageLoader = ImageLoader
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

    class Retrofit(
        private val name: String = "com.squareup.retrofit2:retrofit:${Versions.retrofit}",
    ) : CharSequence by name {
        val kotlinxConverter by lazy { "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.converter}" }

        override fun toString() = name
    }

    class OkHttp(
        private val name: String = "com.squareup.okhttp3:okhttp:${Versions.okHttp}",
    ) : CharSequence by name {
        val loggingInterceptor by lazy { "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}" }

        override fun toString() = name
    }

    object KotlinX {
        val serialization by lazy { "org.jetbrains.kotlin:kotlin-serialization:${Versions.jvm}" }
        val json by lazy { "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinx}" }
        val protobuf by lazy { "org.jetbrains.kotlinx:kotlinx-serialization-protobuf:${Versions.kotlinx}" }
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

    object Test {
        val jUnit by lazy { "junit:junit:${Versions.junit}" }
        val jUnitExt by lazy { "androidx.test.ext:junit:${Versions.junitExt}" }
        val expresso by lazy { "androidx.test.espresso:espresso-core:${Versions.espresso}" }
    }
}