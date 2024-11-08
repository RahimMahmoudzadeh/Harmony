plugins {
    libs.plugins.run {
        alias(android.application)
        alias(kotlin.android)
        alias(ksp)
        alias(kotlin.compose)
        alias(hilt.plugin)
    }
}

android {
    namespace = "com.rahim.harmony"
    compileSdk = 35
    buildFeatures {
        compose = true
        buildConfig = true
    }
    defaultConfig {
        applicationId = "com.rahim.harmony"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.0"

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

}

dependencies {
    libs.run {
        implementation(androidx.core.ktx)

        testImplementation(junit)
        androidTestImplementation(androidx.junit)
        androidTestImplementation(androidx.espresso.core)
        androidTestImplementation(platform(androidx.compose.bom))
        androidTestImplementation(androidx.ui.test.junit4)
        debugImplementation(androidx.ui.tooling)
        debugImplementation(androidx.ui.test.manifest)
        implementation(platform(androidx.compose.bom))
        implementation(bundles.compose)
        //hilt
        implementation(bundles.hilt)
        ksp(hilt.compiler)
    }
}