package com.electro.essential.validator

import com.electro.essential.exception.base.BaseValidationException
import com.electro.essential.exception.validator.EmptyInputFieldException
import com.electro.essential.exception.validator.InputRegexException
import com.electro.essential.resources.AuthValidationStringProvider
import com.electro.essential.resources.ValidationStringProvider
import jdk.internal.net.http.common.Log.errors
import kotlinx.collections.immutable.ImmutableMap
import kotlinx.collections.immutable.persistentMapOf
import kotlinx.collections.immutable.toImmutableMap

sealed interface BaseInputField<T : Any> {

    fun validate(value: T): ValidationResult

    interface TextInputField: BaseInputField<String> {

        fun getFieldName(provider: ValidationStringProvider): String

        val regexMap: ImmutableMap<Regex, ((ValidationStringProvider) -> String)?>

        override fun validate(value: String): ValidationResult {
            val trimmedValue = value.trim()
            val errors = mutableListOf<BaseValidationException>()

            if (trimmedValue.isEmpty()) {
                return ValidationResult.Error(EmptyInputFieldException(this))
            }

            if (regexMap.isNotEmpty()) {
                val failingRegexEntries = regexMap.entries.filter { (regex, _) ->
                    !regex.matches(trimmedValue)
                }
                failingRegexEntries.forEach { (_, customErrorMessage) ->
                    errors.add(InputRegexException(this, customErrorMessage))
                }
            }

            return if (errors.isNotEmpty()) {
                ValidationResult.Error(errors)
            } else {
                ValidationResult.Success
            }
        }
    }

    sealed class AuthInputField(
        private val fieldName: (AuthValidationStringProvider) -> String,
        private val regexErrorResolverMap: ImmutableMap<Regex, ((AuthValidationStringProvider) -> String)?> = persistentMapOf(),
    ): TextInputField {
        override fun getFieldName(provider: ValidationStringProvider): String {
            return if(provider is AuthValidationStringProvider) {
                fieldName(provider)
            } else {
                throw IllegalArgumentException("For AuthInputField you should use AuthValidationStringProvider")
            }
        }

        override val regexMap: ImmutableMap<Regex, ((ValidationStringProvider) -> String)?>
            get() = regexErrorResolverMap.mapValues { (_, resolver) ->
                resolver?.let { specificResolver ->
                    { provider: ValidationStringProvider ->
                        if (provider is AuthValidationStringProvider) {
                            specificResolver(provider)
                        } else {
                            throw IllegalArgumentException("Auth provider required for error resolution.")
                        }
                    }
                }
            }.toImmutableMap()
    }
}

