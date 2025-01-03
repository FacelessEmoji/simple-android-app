plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.androidx.navigation.safe.args)
    kotlin("plugin.serialization") version "1.6.0"
    kotlin("kapt") version "1.9.0"
}

android {
    namespace = "rut.miit.simpleapp"
    compileSdk = 34

    defaultConfig {
//        javaCompileOptions {
//            annotationProcessorOptions {
//                arguments["room.incremental"] = "true"
//                arguments["room.incremental"] = "true"
//                arguments["room.schemaLocation"] = "$projectDir/schemas"
//            }
//        }

        kapt {
            arguments {
                mapOf("room.schemaLocation" to "$projectDir/schemas")
            }
        }

        applicationId = "rut.miit.simpleapp"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        dataBinding = true
        viewBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14" // Обновлено до 1.5.14
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
//    // Room components
//    implementation("androidx.room:room-runtime:2.2.5")
//    kapt("androidx.room:room-compiler:2.2.5")
//    implementation("androidx.room:room-ktx:2.2.5")
//    androidTestImplementation("androidx.room:room-testing:2.2.5")
//
//    //DataBinding
//    kapt("com.android.databinding:compiler:3.2.0-alpha10")
    implementation ("androidx.room:room-runtime:2.5.0") // Библиотека "Room"
    kapt ("androidx.room:room-compiler:2.5.0") // Кодогенератор
    implementation ("androidx.room:room-ktx:2.5.0")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")


    implementation(libs.androidx.preference.ktx)
    val nav_version = "2.8.2"
    // Navigation
    implementation("androidx.navigation:navigation-compose:$nav_version")
    implementation("androidx.navigation:navigation-fragment:$nav_version")
    implementation("androidx.navigation:navigation-ui:$nav_version")
    implementation("androidx.navigation:navigation-dynamic-features-fragment:$nav_version")
    androidTestImplementation("androidx.navigation:navigation-testing:$nav_version")
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")

    //Ktor
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.serialization.core)
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation("io.ktor:ktor-client-logging:2.0.0")

    //slf4j
    implementation(libs.slf4j.api)
    implementation(libs.slf4j.android)

    // Other
    implementation("androidx.compose.material3:material3:1.3.0")
    implementation("androidx.compose.ui:ui:1.7.2")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.recyclerview)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
