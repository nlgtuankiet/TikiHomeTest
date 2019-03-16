import kotlin.String

/**
 * Find which updates are available by running
 *     `$ ./gradlew buildSrcVersions`
 * This will only update the comments.
 *
 * YOU are responsible for updating manually the dependency version. */
object Versions {
    const val appcompat: String = "1.1.0-alpha02" 

    const val constraintlayout: String = "1.1.3" 

    const val core_ktx: String = "1.1.0-alpha04" 

    const val androidx_databinding: String = "3.3.0" // available: "3.3.2"

    const val androidx_lifecycle: String = "2.1.0-alpha01" 

    const val recyclerview: String = "1.1.0-alpha02" 

    const val espresso_core: String = "3.1.1" 

    const val androidx_test_ext_junit: String = "1.1.0" 

    const val androidx_test_core: String = "1.0.0" // available: "1.1.0"

    const val epoxy_databinding: String = "2.6.0" // available: "3.3.1"

    const val epoxy_processor: String = "3.3.1" 

    const val epoxy: String = "3.3.1" 

    const val mvrx: String = "0.7.2" 

    const val aapt2: String = "3.3.0-5013011" // available: "3.3.2-5309881"

    const val com_android_tools_build_gradle: String = "3.3.0" // available: "3.3.2"

    const val lint_gradle: String = "26.3.0" // available: "26.3.2"

    const val com_diffplug_gradle_spotless_gradle_plugin: String = "3.17.0" // available: "3.19.0"

    const val com_google_dagger: String = "2.20" // available: "2.21"

    const val mockito_kotlin: String = "2.0.0" // available: "2.1.0"

    const val com_squareup_inject: String = "0.3.3" 

    const val converter_moshi: String = "2.3.0" // available: "2.5.0"

    const val retrofit: String = "2.5.1-SNAPSHOT" 

    const val de_fayard_buildsrcversions_gradle_plugin: String = "0.3.2" 

    const val junit_junit: String = "4.12" 

    const val org_jetbrains_kotlin: String = "1.3.20" // available: "1.3.30-eap-45"

    const val org_jetbrains_kotlinx: String = "1.1.1" 

    const val robolectric: String = "4.2" // available: "4.2.1"

    /**
     *
     *   To update Gradle, edit the wrapper file at path:
     *      ./gradle/wrapper/gradle-wrapper.properties
     */
    object Gradle {
        const val runningVersion: String = "4.10.2"

        const val currentVersion: String = "5.2.1"

        const val nightlyVersion: String = "5.4-20190316000113+0000"

        const val releaseCandidate: String = "5.3-rc-3"
    }
}
