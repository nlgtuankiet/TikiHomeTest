plugins {
    id("java-library")
    id("kotlin")
    id("kotlin-kapt")
    id("org.jetbrains.kotlin.plugin.allopen")
}
allOpen {
    annotation("com.sample.tikihometest.domain.util.Mockable")
}
buildscript {
    dependencies {
        classpath(Libs.kotlin_gradle_plugin)
    }
}

dependencies {
    implementation(Libs.kotlin_stdlib_jdk7)
    implementation(Libs.kotlinx_coroutines_core)

    implementation(Libs.dagger)
    kapt(Libs.dagger_compiler)

    testImplementation(Libs.junit_junit)
    testImplementation(Libs.mockito_kotlin)
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
