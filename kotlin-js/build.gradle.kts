plugins {
    kotlin("js") version "1.4.31"
    kotlin("plugin.serialization") version "1.4.31"
}

group = "com.kanawish.samples"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven { url = uri("https://dl.bintray.com/kotlin/kotlinx") }
    maven { url = uri("https://dl.bintray.com/kotlin/kotlin-js-wrappers/") }
}

// NOTE: See https://kotlinlang.org/docs/reference/js-project-setup.html
//   Also found working examples here https://github.com/rjaros/kvision/blob/master/build.gradle.kts
dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-html-js:0.7.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:1.4.3")
    implementation("org.jetbrains:kotlin-extensions:1.0.1-pre.148-kotlin-1.4.30")

    implementation(devNpm("file-loader", "^6.1.0"))

    implementation(npm("firebase", "^8.3.1"))
    implementation(npm("firebaseui", "^4.8.0"))

    implementation(npm("bootstrap", "^4.6.0"))
    implementation(npm("bootstrap-icons", "^1.4.0"))
    implementation(npm("jquery", "^3.6.0"))
    implementation(npm("@popperjs/core", "^2.9.1"))

    // UI addons
    implementation(npm("pikaday", "^1.8.2")) // https://www.npmjs.com/package/pikaday

    implementation(npm("is-sorted", "1.0.5"))

    testImplementation(kotlin("test-js"))
}

kotlin {
    js {
        browser {
            binaries.executable()
            commonWebpackConfig {
            }
            webpackTask {
                cssSupport.enabled = true
            }
            runTask {
                cssSupport.enabled = true
            }
            testTask {
                useKarma {
                    useChromeHeadless()
                    webpackConfig.cssSupport.enabled = true
                }
            }
        }
        useCommonJs()
    }
//  js(IR) { // NOTE: js(IR) doesn't support source-maps yet.
}