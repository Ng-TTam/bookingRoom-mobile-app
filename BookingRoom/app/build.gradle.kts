plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.bookingroom"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.bookingroom"
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
    buildFeatures.dataBinding = true
    buildFeatures.viewBinding = true

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation ("com.github.ibrahimsn98:SmoothBottomBar:1.7.9")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation ("com.google.android.material:material:1.11.0")
    implementation ("com.applandeo:material-calendar-view:1.9.1")

    implementation ("com.google.code.gson:gson:2.10.1")
    implementation ("com.squareup.retrofit2:retrofit:2.1.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.1.0")

    implementation ("com.jakewharton.threetenabp:threetenabp:1.3.1")
    implementation ("androidx.fragment:fragment:1.6.2")
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    implementation ("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation ("androidx.recyclerview:recyclerview:1.4.0-alpha01")
    implementation ("androidx.navigation:navigation-ui-ktx:2.7.7")
    implementation ("androidx.databinding:databinding-adapters:8.3.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}