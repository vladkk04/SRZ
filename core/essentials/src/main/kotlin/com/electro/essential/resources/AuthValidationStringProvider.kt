package com.electro.essential.resources

interface AuthValidationStringProvider: ValidationStringProvider {
    val email: String
    val password: String
    val passwordRegexError: String
    val passwordRegexLength: String
}