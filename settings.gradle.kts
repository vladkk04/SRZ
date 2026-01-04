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

include(":feature")
include(":feature:welcome")
include(":feature:welcome:domain")
include(":feature:welcome:presentation")

include(":feature:signIn")
include(":feature:signIn:domain")
include(":feature:signIn:presentation")

include(":feature:signUp")
include(":feature:signUp:domain")
include(":feature:signUp:presentation")

include(":feature:dataFilling")
include(":feature:dataFilling:domain")
include(":feature:dataFilling:presentation")

include(":feature:forgotPassword")
include(":feature:forgotPassword:domain")
include(":feature:forgotPassword:presentation")

include(":feature:fisherman")
include(":feature:fisherman:domain")
include(":feature:fisherman:presentation")

include(":feature:inspector")
include(":feature:inspector:domain")
include(":feature:inspector:presentation")

include(":feature:language")
include(":feature:language:domain")
include(":feature:language:presentation")

include(":feature:licenses")
include(":feature:licenses:domain")
include(":feature:licenses:presentation")

include(":feature:profile")
include(":feature:profile:domain")
include(":feature:profile:presentation")

//Fishing Session Feature
include(":feature:fishingSession")

include(":feature:fishingSession:selectFishingSpot")
include(":feature:fishingSession:selectFishingSpot:domain")
include(":feature:fishingSession:selectFishingSpot:presentation")

include(":feature:fishingSession:session")
include(":feature:fishingSession:session:domain")
include(":feature:fishingSession:session:presentation")

include(":feature:fishingSession:addCaughtFish")
include(":feature:fishingSession:addCaughtFish:domain")
include(":feature:fishingSession:addCaughtFish:presentation")
