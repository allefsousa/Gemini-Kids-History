plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.secretsGradle)
}

android {
    namespace = "com.digitalhorizons.kidshistory"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.digitalhorizons.kidshistory"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        viewBinding {
            enable = true
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.polarisdesignsystem)
    implementation ("io.insert-koin:koin-android:3.5.0")
    implementation ("io.insert-koin:koin-core:3.5.0")
    implementation("androidx.activity:activity-ktx:1.8.2")
    implementation("com.google.ai.client.generativeai:generativeai:0.1.2")
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}