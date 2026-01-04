package com.electro.fish.data.account.auth.model

import com.auth0.android.jwt.JWT
import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class AuthToken(val token: String)

fun AuthToken.getRole(): Role =
    JWT(token).getClaim("role").asObject(Role::class.java)!!



