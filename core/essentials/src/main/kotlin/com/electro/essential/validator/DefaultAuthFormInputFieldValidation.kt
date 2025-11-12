package com.electro.essential.validator

import com.electro.essential.resources.AuthValidationStringProvider
import com.electro.essential.validator.BaseInputField.TextInputField

sealed interface DefaultAuthFormInputFieldValidation {

    data object Email: TextInputField(
        fieldName = { (it as AuthValidationStringProvider).email },
        regex = InputFieldPatternRegex.emailRegex
    )

    data object Password: TextInputField(
        fieldName = { (it as AuthValidationStringProvider).password },
        regexMessage = { (it as AuthValidationStringProvider).passwordRegexError },
        regex = InputFieldPatternRegex.passwordRegex
    )
}