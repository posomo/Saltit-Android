plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = Configuration.javaVersion
    targetCompatibility = Configuration.javaVersion
}

dependencies {
    implementation(Dependencies.jsonParser.gson)
}