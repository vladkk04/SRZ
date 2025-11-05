pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
}

rootProject.name = "SRZApplication"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":data")

include(":core")
include(":core:ui")
include(":core:data")
include(":core:essentials")
include(":core:commonAndroid")

include(":navigation")

include(":feature:welcome")
include(":feature:welcome:domain")
include(":feature:welcome:presentation")

include(":feature:signIn")
include(":feature:signIn:domain")
include(":feature:signIn:presentation")

include(":feature:signUp")
include(":feature:signUp:domain")
include(":feature:signUp:presentation")

include(":feature:admin")
include(":feature:admin:domain")
include(":feature:admin:presentation")

include(":feature:supervisor")
include(":feature:supervisor:domain")
include(":feature:supervisor:presentation")

include(":feature:inspector")
include(":feature:inspector:domain")
include(":feature:inspector:presentation")