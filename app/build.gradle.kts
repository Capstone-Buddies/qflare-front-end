plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.dicoding.Capstone"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.dicoding.Capstone"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
        viewBinding = true
        buildConfig = true
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation("pl.droidsonroids.gif:android-gif-drawable:1.2.28")
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui.ktx)
    //API
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    //Profile
    implementation ("de.hdodenhof:circleimageview:3.1.0")

    implementation ("androidx.viewpager2:viewpager2:1.0.0")
    implementation ("androidx.fragment:fragment-ktx:1.3.6")
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation("androidx.datastore:datastore-preferences:1.0.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.activity:activity-ktx:1.8.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
    //Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")
}