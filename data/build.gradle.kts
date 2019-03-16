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
    implementation(Libs.converter_moshi)
    implementation(Libs.dagger)
    implementation(Libs.kotlin_stdlib_jdk7)
    implementation(Libs.kotlinx_coroutines_core)
    implementation(Libs.retrofit)
    implementation(project(":domain"))
    kapt(Libs.dagger_compiler)
    testImplementation(Libs.androidx_test_core)
    testImplementation(Libs.junit_junit)
    testImplementation(Libs.mockito_kotlin)
    testImplementation(Libs.robolectric)
}
