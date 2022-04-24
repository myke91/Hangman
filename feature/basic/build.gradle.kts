plugins {
    id(Plugins.androidLibrary)
    kotlin(Plugins.kotlinAndroid)
    kotlin(Plugins.kotlinKapt)
    id(Plugins.hilt)
}


android {
    compileSdk = AppConfig.Versions.compileSdk

    defaultConfig {
        minSdk = AppConfig.Versions.minSdk
        targetSdk = AppConfig.Versions.targetSdk

        testInstrumentationRunner = AppConfig.testInstrumentationRunner
        consumerProguardFiles("consumer-rules.pro")
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
        jvmTarget =  JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
}

dependencies {
    implementation(Libs.Kotlin.stdlib)
    implementation(Libs.AndroidX.coreKtx)
    implementation(Libs.AndroidX.appCompat)

    implementation(Libs.AndroidX.Datastore.preferences)

    implementation(Libs.Google.material)

    implementation(Libs.Hilt.hilt)
    implementation(Libs.Hilt.navigation)
    kapt(Libs.Hilt.compiler)

    implementation(Libs.AndroidX.Lifecycle.viewmodel)
    implementation(Libs.AndroidX.Lifecycle.compose)
    implementation(Libs.AndroidX.Lifecycle.runtime)
    implementation(Libs.AndroidX.activityCompose)


    implementation(Libs.AndroidX.Navigation.compose)

    implementation(Libs.AndroidX.Compose.ui)
    implementation(Libs.AndroidX.Compose.material)
    implementation(Libs.AndroidX.Compose.constraintLayout)

    implementation(Libs.AndroidX.Room.room)
    kapt(Libs.AndroidX.Room.compiler)


    testImplementation(Libs.Test.junit)
    testImplementation(Libs.Test.Mockito.inline)
    testImplementation(Libs.Test.MockitoKotlin.kotlin) {
        exclude(group = "org.jetbrains.kotlin", module = "kotlin-stdlib")
        exclude(group = "org.jetbrains.kotlin", module = "kotlin-stdlib-common")
    }

    androidTestImplementation(Libs.Test.androidxJunitExt)

}