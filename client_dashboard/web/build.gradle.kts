import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

group = "org.thechance"
version = "1.0-SNAPSHOT"

kotlin {
    js(IR) {
        browser()
        binaries.executable()
    }

    sourceSets {
        val jsMain by getting {
            dependencies {
//                implementation(project(":client_dashboard:common"))
//                implementation(project(":design_system:shared"))
                implementation("io.github.thechance101:chart:Beta-0.0.5")
                implementation(libs.compose.runtime)
                implementation(compose.ui)
                implementation(libs.compose.foundation)
                implementation(libs.compose.material3)
            }
        }
    }
}


compose.experimental {
    web.application {}
}

compose {
    val composeVersion = project.property("compose.wasm.version") as String
    kotlinCompilerPlugin.set(composeVersion)
    val kotlinVersion = project.property("kotlin.version") as String
    kotlinCompilerPluginArgs.add("suppressKotlinVersionCompatibilityCheck=$kotlinVersion")
}