package com.electro.domain.model

import com.electro.domain.resources.SignUpStringProvider
import com.electro.essential.validator.TextInputField

sealed interface InputField {

   /* val fieldName: SignUpStringProvider.() -> String

    private companion object {
        private const val MIN_PASSWORD_LENGTH = 6
    }

    sealed class FirstName(
        override val fieldName: SignUpStringProvider.() -> String
    ): InputField, TextInputField {
        override val label: String = { fieldName }
        override val maxLength: Int? = null
        override val minLength: Int? = null
        override val regex: Regex? = null
    }

    data object LastName: TextInputField {
        override val label: String = = { emailLabel }
        override val maxLength: Int? = null
        override val minLength: Int? = null
        override val regex: Regex? = null
        override val isRequired: Boolean = true
    }

    data object DateBirthdayName: TextInputField {
        override val label: String = = { emailLabel }
        override val maxLength: Int? = null
        override val minLength: Int? = null
        override val regex: Regex? = null
        override val isRequired: Boolean = false
    }

    data object Email: TextInputField {
        override val label: String = = { emailLabel }
        override val maxLength: Int? = null
        override val minLength: Int? = null
        override val regex: Regex? = null
        override val isRequired: Boolean = true
    }

    data object Password: TextInputField {
        override val label: String = = { passwordLabel }
        override val maxLength: Int? = null
        override val minLength: Int? = MIN_PASSWORD_LENGTH
        override val regex: Regex? = null
        override val isRequired: Boolean = true
    }

    data object RepeatPassword: TextInputField {
        override val label: String = = { repeatPasswordLabel }
        override val maxLength: Int? = null
        override val minLength: Int? = MIN_PASSWORD_LENGTH
        override val regex: Regex? = null
        override val isRequired: Boolean = true
    }*/




}