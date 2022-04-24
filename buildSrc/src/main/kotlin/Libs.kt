object Libs {
    object Kotlin {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    }

    object Google {
        const val material = "com.google.android.material:material:${Versions.material}"
    }

    object AndroidX {

        object Compose {
            const val ui = "androidx.compose.ui:ui:${Versions.compose}"
            const val material = "androidx.compose.material:material:${Versions.compose}"
            const val constraintLayout = "androidx.constraintlayout:constraintlayout-compose:${Versions.composeConstraintLayout}"
        }

        object Navigation {
            const val compose = "androidx.navigation:navigation-compose:${Versions.navigation}"
        }

        object Room {
            const val room = "androidx.room:room-ktx:${Versions.room}"
            const val compiler = "androidx.room:room-compiler:${Versions.room}"
        }

        object Datastore {
            const val preferences = "androidx.datastore:datastore-preferences:${Versions.datastore}"
        }

        object Lifecycle {
            const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
            const val compose =
                "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycle}"
            const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
        }

        const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
        const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
        const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"
    }

    object Hilt {
        const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val navigation = "androidx.hilt:hilt-navigation-compose:${Versions.navigationCompose}"
        const val compiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    }


    object Test {
        object MockitoKotlin {
            const val kotlin =
                "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"
        }

        object Mockito {
            const val inline = "org.mockito:mockito-inline:${Versions.mockito}"
            const val android = "org.mockito:mockito-android:${Versions.mockito}"
        }


        const val junit = "junit:junit:${Versions.junit}"
        const val runner = "androidx.test:runner:${Versions.test}"
        const val rules = "androidx.test:rules:${Versions.test}"
        const val androidxJunitExt = "androidx.test.ext:junit:${Versions.ext}"
        const val composeUiJunit = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
        const val composeUiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
        const val composeUiManifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose}"
    }
}