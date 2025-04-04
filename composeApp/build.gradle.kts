//Això està al plugin composeMultiplatform
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
//import org.jetbrains.compose.compose
//Això està al  plugin kotlinMultiplatform
//import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
//import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
//import org.jetbrains.kotlin.gradle.dsl.JvmTarget
//import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpack
//import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    //alialibs.pluginsns.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)

    //SQL libs.plugins
    //id("libs.pluginsdelight") version "2.0.2"
    alias(libs.plugins.sqldelight)
    kotlin("plugin.serialization") version "1.8.20"
}

sqldelight {
    databases {
        create("Database") {
            packageName.set("cat.itb.dam.m78.dbdemo3.db")
            schemaOutputDirectory.set(file("src/commonMain/sqldelight/databases"))
            //verifyMigrations.set(true)
        }
    }
}
repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

//Bloque de configuración para Kotlin Multiplatform
kotlin {

    //Target JVM
    jvm("desktop")
    jvmToolchain(11)

    //Target Android

//    androidTarget {
//        @OptIn(ExperimentalKotlinGradlePluginApi::class)
//        compilerOptions {
//            jvmTarget.set(JvmTarget.JVM_11)
//        }
//    }
//
//    //Terget Wasm
//    @OptIn(ExperimentalWasmDsl::class)
//    wasmJs {
//        moduleName = "composeApp"
//        browser {
//            val rootDirPath = project.rootDir.path
//            val projectDirPath = project.projectDir.path
//            commonWebpackConfig {
//                outputFileName = "composeApp.js"
//                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
//                    static = (static ?: mutableListOf()).apply {
//                        // Serve sources to debug inside browser
//                        add(rootDirPath)
//                        add(projectDirPath)
//                    }
//                    open = mapOf("app" to mapOf(
//                                "name" to "google chrome", // "edge"
//                                "arguments" to listOf("--js-flags=--experimental-wasm-gc")
//                                )
//                            )
//                }
//            }
//        }
//        binaries.executable()
//    }

    //Bloque de configuración de dependències
    sourceSets {

        val ioVer = "3.1.0"
        val jetVer = "1.7.0"
        commonMain.dependencies {
            //implementation(project.dependencies.platform("androidx.compose:compose-bom:2025.03.00"))
            //implementation("androidx.navigation:navigation-compose")
            //implementation("androidx.lifecycle:lifecycle-runtime:2.8.5")
            //implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
            //runtimeOnly("org.jetbrains.compose.ui:ui-text:1.7.3")
            //implementation(compose.desktop.currentOs)
            //implementation("org.jetbrains.compose.ui:ui-util")
            //implementation("androidx.compose.ui:ui-util:1.7.8")
            implementation("io.ktor:ktor-serialization-kotlinx-json:$ioVer")
            implementation("io.ktor:ktor-client-core:$ioVer")
            implementation("io.ktor:ktor-client-cio:$ioVer")
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
            implementation("io.ktor:ktor-client-content-negotiation:$ioVer")
            //implementation("io.coil-kt.coil3:coil-compose:$ioVer")
            //implementation("io.coil-kt.coil3:coil-network-ktor3:$ioVer")
            //implementation("com.russhwolf:multiplatform-settings-no-arg:1.3.0")
            //implementation("com.russhwolf:multiplatform-settings-serialization:1.3.0")
            implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.1")
            //implementation("org.jetbrains.compose.runtime:runtime:1.7.1")
            implementation("org.jetbrains.compose.ui:ui-util:$jetVer")
            implementation("org.jetbrains.compose.desktop:desktop:$jetVer")
            //implementation("org.jetbrains.compose.foundation:foundation:$jetVer")
            //implementation("org.jetbrains.compose.material:material:$jetVer")
            //implementation("org.jetbrains.compose.ui:ui:$jetVer")
            //implementation(libs.androidx.ui.android)
            //implementation("androidx.compose.material3:material3:1.3.1")
            implementation("androidx.compose.compiler:compiler:1.5.15")
            implementation("com.arkivanov.decompose:decompose:2.0.0")
            implementation("com.arkivanov.decompose:extensions-compose-jetbrains:2.0.0")

            implementation(compose.material3)
            //implementation(compose.runtime)
            //implementation(compose.foundation)
            //implementation(compose.material)
            //implementation(compose.ui)
            //implementation(compose.components.resources)
            //implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(libs.androidx.navigation.runtime.desktop)
            //implementation(libs.androidx.navigation.compose)
            implementation(compose.desktop.currentOs)
            //implementation(libs.androidx.material3)
            //implementation("io.ktor:ktor-client-android:$ioVer")
            //implementation("io.ktor:ktor-server-content-negotiation:$ioVer")
            //implementation("org.slf4j:slf4j-api:2.0.17")
            //implementation("org.slf4j:slf4j-simple:2.0.17")
            //SQL Delight
            //implementation(libs.delight.coroutines.extensions)

        }

        val desktopMain by getting //Això és per que té un nom diferent al defalut
        desktopMain.dependencies {
            //Sql Delight
            implementation(libs.delight.desktop.driver)
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)

        }

//        androidMain.dependencies {
//            implementation(compose.preview)
//            implementation(libs.androidx.activity.compose)
//            //Sql Delight
//            implementation(libs.delight.android.driver)
//
//        }
//        nativeMain.dependencies {
//            implementation(libs.delight.native.driver)
//        }
    }
}

//Configuraciones específicas para escritorio
compose.desktop {
    application {
        mainClass = "cat.itb.dam.m78.dbdemo3.view.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "cat.itb.dam.m78.dbdemo3"
            packageVersion = "1.0.0"
        }
    }
}

//Configuraciones específicas de Android
//android {
//    namespace = "cat.itb.dam.m78.dbdemo3"
//    compileSdk = libs.versions.android.compileSdk.get().toInt()
//
//    defaultConfig {
//        applicationId = "cat.itb.dam.m78.dbdemo3"
//        minSdk = libs.versions.android.minSdk.get().toInt()
//        targetSdk = libs.versions.android.targetSdk.get().toInt()
//        versionCode = 1
//        versionName = "1.0"
//    }
//    packaging {
//        resources {
//            excludes += "/META-INF/{AL2.0,LGPL2.1}"
//        }
//    }
//    buildTypes {
//        getByName("release") {
//            isMinifyEnabled = false
//        }
//    }
//    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_11
//        targetCompatibility = JavaVersion.VERSION_11
//    }
//}

//dependencies {
//    debugImplementation(compose.uiTooling)
//}

