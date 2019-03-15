plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")

}

android {
    compileSdkVersion(28)
    dataBinding {
        isEnabled = true
    }
    defaultConfig {
        applicationId = "com.sample.tikihometest"
        minSdkVersion(21)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    signingConfigs {
        getByName("debug") {
            storeFile = rootProject.file("debug.keystore")
            storePassword = "android"
            keyAlias = "androiddebugkey"
            keyPassword = "android"
        }
    }
    buildTypes {
        getByName("debug") {
//            resValue("string", "app_name", "Tiki Home Test Dev")
            applicationIdSuffix = ".debug"
            signingConfigs.getByName("debug")
            isDebuggable = true
            isMinifyEnabled = false
            isShrinkResources = false
        }
    }
}

dependencies {
    implementation(Libs.kotlin_stdlib_jdk7)
    implementation(Libs.appcompat)
    implementation(Libs.core_ktx)
    implementation(Libs.constraintlayout)
    implementation(Libs.lifecycle_viewmodel_ktx)
    implementation(Libs.lifecycle_livedata_ktx)
    implementation(Libs.lifecycle_extensions)
    testImplementation(Libs.junit_junit)
    androidTestImplementation(Libs.androidx_test_ext_junit)
    androidTestImplementation(Libs.espresso_core)
    testImplementation(Libs.robolectric)

    implementation(Libs.dagger)
    kapt(Libs.dagger_compiler)
    implementation(Libs.dagger_android)
    implementation(Libs.dagger_android_support)
    kapt(Libs.dagger_android_processor)

    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(Libs.recyclerview)
    testImplementation(Libs.mockito_kotlin)
    implementation(Libs.kotlinx_coroutines_android)
    implementation("com.airbnb.android:mvrx:0.7.2")
    implementation("com.squareup.inject:assisted-inject-annotations-dagger2:0.3.3")
    kapt("com.squareup.inject:assisted-inject-processor-dagger2:0.3.3")
}

kapt {
    arguments {
        arg("dagger.formatGeneratedSource", "disabled")
        arg("dagger.gradle.incremental", "enabled")
    }
    javacOptions {
        option("-Xmaxerrs", 500)
    }
    useBuildCache = true
}
