import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties

plugins {
  alias(libs.plugins.kotlinMultiplatform)
  alias(libs.plugins.androidApplication)
  alias(libs.plugins.composeMultiplatform)
  alias(libs.plugins.composeHotReload)
  alias(libs.plugins.composeCompiler)
  alias(libs.plugins.kotlin.serialization)
  alias(libs.plugins.room)
  alias(libs.plugins.ksp)
}

kotlin {
  androidTarget {
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    compilerOptions {
      jvmTarget.set(JvmTarget.JVM_11)
    }
  }

  listOf(
    iosX64(),
    iosArm64(),
    iosSimulatorArm64()
  ).forEach { iosTarget ->
    iosTarget.binaries.framework {
      baseName = "ComposeApp"
      isStatic = true
    }
  }

  sourceSets {

    androidMain.dependencies {
      implementation(compose.preview)
      implementation(libs.androidx.activity.compose)

      implementation(libs.ktor.client.okhttp)
      implementation(libs.androidx.core.splashscreen)
    }
    commonMain.dependencies {
      implementation(compose.runtime)
      implementation(compose.foundation)
      implementation(compose.material3)
      implementation(compose.ui)
      implementation(compose.components.resources)
      implementation(compose.components.uiToolingPreview)
      implementation(libs.androidx.lifecycle.viewmodel)
      implementation(libs.androidx.lifecycle.runtimeCompose)

      implementation(libs.bundles.ktor)
      implementation(libs.coil.compose)
      implementation(libs.coil.network.ktor)

      implementation(libs.androidx.navigation.compose)

      implementation(libs.androidx.room.runtime)
      implementation(libs.sqlite.bundled)
    }
    commonTest.dependencies {
      implementation(libs.kotlin.test)
    }
    iosMain.dependencies {
      implementation(libs.ktor.client.darwin)
    }
  }
}

tasks.register("generateSecretsClass") {
  val outputFile =
    File("$projectDir/src/commonMain/kotlin/dev/jesusgonzalez/kotlinproject/BuildProps.kt")
  val inputFile =
    rootProject.file("secrets.properties")
  doLast {
    if (inputFile.exists()) {
      val properties = Properties()
      inputFile.inputStream().use { properties.load(it) }
      print(outputFile.absolutePath)
      outputFile.parentFile.mkdirs()
      val propertiesString =
        properties.entries.joinToString(separator = "\n          ") { (key, value) ->
          val propKey = key.toString()
          val propValue = value.toString()
          "val ${propKey.lowercase().replace("_", "")} : String = \"${propValue}\""
        }

      outputFile.writeText(
        """
        package dev.jesusgonzalez.kotlinproject

        object BuildProps {
          ${propertiesString.trim()}
        }
        """.trimIndent() + "\n"
      )
    }
  }
}
tasks.named("generateComposeResClass") {
  dependsOn("generateSecretsClass")
}

android {
  namespace = "dev.jesusgonzalez.kotlinproject"
  compileSdk = libs.versions.android.compileSdk.get().toInt()

  defaultConfig {
    applicationId = "dev.jesusgonzalez.kotlinproject"
    minSdk = libs.versions.android.minSdk.get().toInt()
    targetSdk = libs.versions.android.targetSdk.get().toInt()
    versionCode = 1
    versionName = "1.0"
  }
  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
  buildTypes {
    getByName("release") {
      isMinifyEnabled = false
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
}

dependencies {
  debugImplementation(compose.uiTooling)
  ksp(libs.androidx.room.compiler)
}

room {
  schemaDirectory("$projectDir/schemas")
}