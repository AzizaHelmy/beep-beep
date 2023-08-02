import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

group = "org.thechance"
version = "1.0-SNAPSHOT"


kotlin {
    jvm {
        jvmToolchain(libs.versions.jvmToolchain.get().toInt())
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(project(":client_dashboard:common"))
                implementation(compose.desktop.currentOs)
                implementation(project(":design_system:shared"))
            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "client_dashboard"
            packageVersion = "1.0.0"
        }
    }
}
