package com.electro.essential.validator

object InputFieldPatternRegex {
    val emailRegex = Regex(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )
    val passwordRegex = Regex("^(?=.*[A-Za-z])(?=.*[^A-Za-z0-9]).+$")
}