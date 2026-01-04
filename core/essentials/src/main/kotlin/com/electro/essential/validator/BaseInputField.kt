package com.electro.essential.validator

import com.electro.essential.exception.base.BaseValidationException
import com.electro.essential.exception.validator.EmptyImageException
import com.electro.essential.exception.validator.EmptyInputFieldException
import com.electro.essential.exception.validator.InputRegexException
import com.electro.essential.resources.ValidationStringProvider
import com.sun.jndi.toolkit.url.Uri
import kotlinx.collections.immutable.ImmutableMap
import kotlinx.collections.immutable.persistentMapOf
import kotlinx.collections.immutable.toImmutableMap

sealed interface BaseInputField<T : Any?> {

    fun validate(value: T): ValidationResult

    interface InputField: BaseInputField<String> {

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

    interface TextInputField<T : ValidationStringProvider>: InputField {
        val fieldName: (T) -> String
        val regexErrorResolverMap: ImmutableMap<Regex, ((T) -> String)?>
            get() = persistentMapOf()

        override fun getFieldName(provider: ValidationStringProvider): String {
            @Suppress("UNCHECKED_CAST")
            return try {
                fieldName(provider as T)
            } catch (e: ClassCastException) {
                throw IllegalArgumentException("The provided ValidationStringProvider is not of the expected type for this TextInputField field.", e)
            }
        }
        override val regexMap: ImmutableMap<Regex, ((ValidationStringProvider) -> String)?>
            get() = regexErrorResolverMap.mapValues { (_, resolver) ->
                resolver?.let { specificResolver ->
                    { provider: ValidationStringProvider ->
                        @Suppress("UNCHECKED_CAST")
                        specificResolver(provider as T)
                    }
                }
            }.toImmutableMap()
    }

    interface ImageInputField<T : ValidationStringProvider>: BaseInputField<String?> {
        val fieldName: (T) -> String

        fun getFieldName(provider: ValidationStringProvider): String {
            @Suppress("UNCHECKED_CAST")
            return try {
                fieldName(provider as T)
            } catch (e: ClassCastException) {
                throw IllegalArgumentException("Provider type mismatch", e)
            }
        }

        override fun validate(value: String?): ValidationResult {
            val errors = mutableListOf<BaseValidationException>()

            if (value.isNullOrEmpty()) {
                errors.add(EmptyImageException(this))
            }

            return if (errors.isNotEmpty()) {
                ValidationResult.Error(errors)
            } else {
                ValidationResult.Success
            }
        }
    }
}

