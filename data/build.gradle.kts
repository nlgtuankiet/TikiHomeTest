plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(28)
    defaultConfig {
        minSdkVersion(21)
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    testOptions {
        unitTests.apply {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    implementation(Libs.kotlin_stdlib_jdk7)
    implementation(Libs.retrofit)
    testImplementation(Libs.robolectric)
    testImplementation(Libs.junit_junit)
    testImplementation(Libs.androidx_test_core)
    implementation(Libs.converter_moshi)
    implementation(Libs.kotlinx_coroutines_core)
    testImplementation(Libs.mockito_kotlin)
    implementation(project(":domain"))
    implementation(Libs.dagger)
    kapt(Libs.dagger_compiler)
}
