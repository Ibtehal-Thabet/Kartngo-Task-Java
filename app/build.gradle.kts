plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.kartngotaskjava"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.kartngotaskjava"
        minSdk = 24
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures{
        dataBinding = true
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.7.1")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.1")

    implementation("androidx.room:room-runtime:2.2.5")
    annotationProcessor("androidx.room:room-compiler:2.2.5")

    implementation("androidx.lifecycle:lifecycle-viewmodel:2.2.0")
    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata:2.2.0")

    annotationProcessor("androidx.lifecycle:lifecycle-compiler:2.2.0")

    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.2.0")

    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")


    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
}