package com.electro.essential.resources

interface CoreStringProvider: StringProvider {
    val connectionErrorMessage: String
    val unknownErrorMessage: String
    fun serverErrorMessage(code: Int, message: String): String
}