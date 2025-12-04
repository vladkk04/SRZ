package com.electro.essential.validator

import kotlinx.collections.immutable.persistentMapOf

sealed interface DefaultAuthFormInputFieldValidation {

    data object Email : BaseInputField.AuthInputField(
        fieldName = { it.email },
        regexErrorResolverMap = persistentMapOf(InputFieldPatternRegex.emailRegex to null)
    )

    data object Password : BaseInputField.AuthInputField(
        fieldName = { it.password },
        regexErrorResolverMap = persistentMapOf(
            InputFieldPatternRegex.passwordRegex to { it.passwordRegexError },
            InputFieldPatternRegex.passwordLengthRegex to { it.passwordRegexLength }
        )
    )

    data object PasswordWithoutRegex : BaseInputField.AuthInputField(
        fieldName = { it.password },
    )
}
