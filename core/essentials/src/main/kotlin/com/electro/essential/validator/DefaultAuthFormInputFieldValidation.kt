package com.electro.essential.validator

import com.electro.essential.resources.AuthValidationStringProvider
import kotlinx.collections.immutable.ImmutableMap
import kotlinx.collections.immutable.persistentMapOf

sealed interface DefaultAuthFormInputFieldValidation: BaseInputField.TextInputField<AuthValidationStringProvider> {

    data object Email : DefaultAuthFormInputFieldValidation {
        override val fieldName: (AuthValidationStringProvider) -> String = { it.email }
        override val regexErrorResolverMap =
            persistentMapOf(InputFieldPatternRegex.emailRegex to null)
    }

    data object Password : DefaultAuthFormInputFieldValidation {
        override val fieldName: (AuthValidationStringProvider) -> String = { it.password }
        override val regexErrorResolverMap: ImmutableMap<Regex, ((AuthValidationStringProvider) -> String)?> = persistentMapOf(
            InputFieldPatternRegex.passwordRegex to { it.passwordRegexError },
            InputFieldPatternRegex.passwordLengthRegex to { it.passwordRegexLength }
        )
    }

    data object PasswordWithoutRegex : DefaultAuthFormInputFieldValidation {
        override val fieldName: (AuthValidationStringProvider) -> String = { it.password }
    }
}
