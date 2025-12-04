package com.electro.fish.data.model

import com.auth0.android.jwt.JWT
import kotlinx.serialization.Serializable

@Serializable
data class AuthToken(
    val token: String
) {
   /* fun getRole(): Role? {
        return JWT(token).getClaim("role").asObject(Role::class.java)
    }*/
}


