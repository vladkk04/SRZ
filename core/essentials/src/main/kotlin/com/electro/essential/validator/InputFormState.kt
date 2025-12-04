package com.electro.essential.validator

import com.electro.essential.exception.base.BaseValidationException
import com.electro.essential.resources.ValidationStringProvider
import jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle
import kotlinx.collections.immutable.ImmutableMap
import kotlinx.collections.immutable.toImmutableMap

sealed interface InputFieldEvent {
    data class Validate(val credentials: WithFormInputValidator) : InputFieldEvent
    data class ClearError(val inputField: BaseInputField<*>) : InputFieldEvent
    data class EnableErrorMessages(val inputField: BaseInputField<*>) : InputFieldEvent
}

data class InputFormState(
    private val stringProvider: ValidationStringProvider,
    private val allErrorMessages: Map<BaseInputField<*>, String> = emptyMap(),
    private val fieldsWithEnabledErrors: Set<BaseInputField<*>> = emptySet()
) {
    val errorMessages: ImmutableMap<BaseInputField<*>, String> =
        allErrorMessages.filterKeys(fieldsWithEnabledErrors::contains).toImmutableMap()

    fun onEvent(event: InputFieldEvent): InputFormState {
        return when (event) {
            is InputFieldEvent.ClearError -> clearError(event.inputField)
            is InputFieldEvent.EnableErrorMessages -> enableErrorMessages(event.inputField)
            is InputFieldEvent.Validate -> withNewValidationResult(event.credentials.validate())
        }
    }

    fun withValidationException(e: BaseValidationException) = copy(
        allErrorMessages = (allErrorMessages + toErrorMessagePair(e)).toImmutableMap(),
        fieldsWithEnabledErrors = fieldsWithEnabledErrors + e.inputField
    )

    private fun withNewValidationResult(validationResult: ValidationResult) = copy(
        allErrorMessages = when (validationResult) {
            is ValidationResult.Error -> validationResult.exceptions.associate(::toErrorMessagePair)
            is ValidationResult.Success -> emptyMap()
        }
    )

    private fun clearError(inputField: BaseInputField<*>) = copy(
        allErrorMessages = allErrorMessages.filterKeys { it != inputField }
    )

    private fun enableErrorMessages(inputField: BaseInputField<*>) = copy(
        fieldsWithEnabledErrors = fieldsWithEnabledErrors + inputField
    )

    private fun toErrorMessagePair(e: BaseValidationException) =
        e.inputField to e.getLocalizedErrorMessage(stringProvider)
}


