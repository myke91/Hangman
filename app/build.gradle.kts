plugins {
    id(Plugins.androidApplication)
    kotlin(Plugins.kotlinAndroid)
    kotlin(Plugins.kotlinKapt)
    id(Plugins.hilt)
}



android {
    compileSdk = AppConfig.Versions.compileSdk

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.Versions.minSdk
        targetSdk = AppConfig.Versions.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = AppConfig.testInstrumentationRunner

        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "room.schemaLocation" to "$projectDir/schemas",
                    "room.incremental" to "true",
                    "room.expandProjection" to "true"
                )
            }
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


    packagingOptions {
        resources.excludes.add("META-INF/com.android.tools/proguard/coroutines.pro")
        resources.excludes.add("META-INF/DEPENDENCIES")
        resources.excludes.add("META-INF/LICENSE")
        resources.excludes.add("META-INF/LICENSE.txt")
        resources.excludes.add("META-INF/license.txt")
        resources.excludes.add("META-INF/NOTICE")
        resources.excludes.add("META-INF/NOTICE.txt")
        resources.excludes.add("META-INF/notice.txt")
        resources.excludes.add("META-INF/ASL2.0")
        resources.excludes.add("META-INF/AL2.0")
        resources.excludes.add("META-INF/LGPL2.1")
        resources.excludes.add("META-INF/licenses/ASM")
        resources.excludes.add("META-INF/*.kotlin_module")
        resources.excludes.add("win32-x86/attach_hotspot_windows.dll")
        resources.excludes.add("win32-x86-64/attach_hotspot_windows.dll")
        resources.excludes.add("mockito-extensions/org.mockito.plugins.MockMaker")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
}

dependencies {
    implementation(project(mapOf("path" to ":feature:basic")))

    implementation(Libs.Kotlin.stdlib)
    implementation(Libs.AndroidX.coreKtx)
    implementation(Libs.AndroidX.appCompat)

    implementation(Libs.Google.material)

    implementation(Libs.AndroidX.Lifecycle.compose)
    implementation(Libs.AndroidX.activityCompose)

    implementation(Libs.AndroidX.Room.room)
    kapt(Libs.AndroidX.Room.compiler)

    implementation(Libs.AndroidX.Navigation.compose)

    implementation(Libs.Hilt.hilt)
    kapt(Libs.Hilt.compiler)


    androidTestImplementation(Libs.Test.androidxJunitExt)
    androidTestImplementation(Libs.Test.composeUiJunit)

    debugImplementation(Libs.Test.composeUiTooling)
    debugImplementation(Libs.Test.composeUiManifest)
}

