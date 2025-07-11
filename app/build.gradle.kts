plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
    id("com.google.gms.google-services")

}

android {
    namespace = "dev.luischang.lab03countryapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "dev.luischang.lab03countryapp"
        minSdk = 31
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.auth)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //Add navigation compose
    implementation("androidx.navigation:navigation-compose:2.8.9")
    //Add coil compose
    implementation("io.coil-kt:coil-compose:2.5.0")
    //Add room
    implementation("androidx.room:room-runtime:2.6.0")
    //Add room ktx
    implementation("androidx.room:room-ktx:2.6.0")
    //Add room compiler
    kapt("androidx.room:room-compiler:2.6.0")
    //Add Material icons
    implementation("androidx.compose.material:material-icons-extended")
    //Add coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    //Add lifecycle viewmodel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")

    implementation(platform("com.google.firebase:firebase-bom:33.14.0"))

    //Import retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    //Import converter
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    //Import okhttp
    implementation("com.squareup.okhttp3:okhttp:4.11.0")
    //Import logging interceptor
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

    //Import markdown
    implementation("com.mikepenz:multiplatform-markdown-renderer-compose:0.2.0")





}