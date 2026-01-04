package com.electro.essential.resources

interface CoreStringProvider: StringProvider {
    fun clientErrorMessage(code: Int, message: String): String
}