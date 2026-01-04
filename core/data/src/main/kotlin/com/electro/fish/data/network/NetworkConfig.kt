package com.electro.fish.data.network

import kotlin.time.Duration.Companion.seconds

object NetworkConfig {
    const val BASE_URL = "http://192.168.68.59:8080/"
    const val AUTH_PATH_URL = "api/auth"

    const val USER_PATH_URL = "api/user"

    const val CATCH_PATH_URL = "api/catch"

    const val SIGN_UP_PATH_URL = "$AUTH_PATH_URL/register"

    const val SIGN_IN_PATH_URL = "$AUTH_PATH_URL/login"
    const val GET_PROFILE_DATA_PATH_URL = "$USER_PATH_URL/me"

    const val FORGOT_PASSWORD_PATH_URL = "$AUTH_PATH_URL/forgot-password"

    const val UPLOAD_PICTURE_PATH_URL = "$USER_PATH_URL/me/upload-photo"

    const val GET_CAUGHT_FISHES_PATH_URL = "$CATCH_PATH_URL/me"

    fun addFishPicture(fishId: Int) = "$CATCH_PATH_URL/$fishId/upload-photo"

    val timeout = 10.seconds
}
