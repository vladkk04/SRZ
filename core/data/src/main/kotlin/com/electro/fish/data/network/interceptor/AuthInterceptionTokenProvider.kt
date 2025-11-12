package com.electro.fish.data.network.interceptor

interface AuthInterceptionTokenProvider {
    fun provideToken(): String?
}