plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    kotlin("kapt") // we have to use kapt, because android hilt is still in alpha although kapt is deprecated already ?! :(
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.hello_world"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.hello_world"
        minSdk = 28
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/INDEX.LIST"
            excludes += "META-INF/DEPENDENCIES"
            excludes += "META-INF/LICENSE.md"
            excludes += "META-INF/NOTICE.md"
            excludes += "META-INF/DEPENDENCIES.txt"
        }
    }
}

dependencies {
    implementation(libs.androidx.ktx)
    implementation(libs.androidx.lifecycle)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.activity.compose)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.compose.ui.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.ui.ui.tooling.preview)
    implementation(libs.compose.material)

    testImplementation(libs.test.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.espresso)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test)

    debugImplementation(libs.debug.compose.ui.tooling)
    debugImplementation(libs.debug.compose.manifest)

    implementation(libs.rxjava.rxjava)
    implementation(libs.rxjava.android)
    implementation(libs.compose.rxjava)

    implementation(libs.dagger.hilt)
    kapt(libs.kapt.hilt)
    implementation(libs.fasterxml.jackson)
    implementation(libs.resteasy.client)
    implementation(libs.smallrye.config)
}
kapt {
    correctErrorTypes = true
}

/** JavaDoc helper.
 * This tasks writes the the class-path to a file that can be used with javadoc
 * javadoc ... @javadoc.txt
 */
tasks.register("javadoc-params") {
    doLast {
        val variant = project.android.applicationVariants
        val release = variant.filter{ it.buildType.name == "release" }.first()
        val outputFile = project.layout.buildDirectory.file("javadoc.txt").get().asFile
        outputFile.printWriter().use { out ->
            val classpath = release.compileConfiguration.joinToString(separator=":") { it.toString() }
            out.println("--class-path " + classpath)
            out.println()
        }
        println("javadoc options written to " + outputFile.absolutePath)
    }
}