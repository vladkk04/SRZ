package com.electro.fish.presentation.dataFilling.pager.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.electro.essential.validator.InputFieldEvent
import com.electro.essential.validator.InputFormState
import com.electro.essential.validator.WithFormInputValidator
import com.electro.fish.domain.model.FullNameInputFieldValue
import com.electro.fish.domain.validator.ProfileSetupInputField
import com.electro.fish.feature.dataFilling.presentation.R
import com.electro.fish.presentation.dataFilling.DataFillingEvent
import com.electro.fish.ui.component.AppOutlinedTextField
import com.electro.fish.ui.component.PagerScope
import com.electro.fish.ui.util.extension.onFocusLost

@Composable
fun PagerScope.FullNameScreen(
    inputValidator: FullNameInputFieldValue,
    inputFormState: InputFormState,
    onEvent: (DataFillingEvent) -> Unit,
    firstName: MutableState<String>,
    lastName: MutableState<String>,
    modifier: Modifier = Modifier,
) {
    val focusRequester = remember { FocusRequester() }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        AppOutlinedTextField(
            onValueChange = {
                firstName.value = it
                onEvent(
                    DataFillingEvent.InputEvent(
                        InputFieldEvent.ClearError(
                            ProfileSetupInputField.FirstName
                        )
                    )
                )
            },
            label = stringResource(R.string.dataFilling_firstName),
            isError = inputFormState.errorMessages.containsKey(ProfileSetupInputField.FirstName),
            errorMessage = inputFormState.errorMessages[ProfileSetupInputField.FirstName],
            imeAction = ImeAction.Next,
            onImeAction = {
                onEvent(
                    DataFillingEvent.InputEvent(InputFieldEvent.Validate(inputValidator))
                )
            },
            modifier = modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .onFocusLost {
                    onEvent(
                        DataFillingEvent.InputEvent(
                            InputFieldEvent.EnableErrorMessages(
                                ProfileSetupInputField.FirstName
                            )
                        )
                    )
                    onEvent(
                        DataFillingEvent.InputEvent(
                            InputFieldEvent.Validate(
                                FullNameInputFieldValue(
                                    firstName = firstName.value,
                                    lastName = lastName.value
                                )
                            )
                        )
                    )
                }
        )

        AppOutlinedTextField(
            onValueChange = {
                lastName.value = it
                onEvent(
                    DataFillingEvent.InputEvent(
                        InputFieldEvent.ClearError(
                            ProfileSetupInputField.LastName
                        )
                    )
                )
            },
            label = stringResource(R.string.dataFilling_lastName),
            isError = inputFormState.errorMessages.containsKey(ProfileSetupInputField.LastName),
            errorMessage = inputFormState.errorMessages[ProfileSetupInputField.LastName],
            imeAction = ImeAction.Done,
            onImeAction = {
                onEvent(
                    DataFillingEvent.InputEvent(InputFieldEvent.Validate(inputValidator))
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .onFocusLost {
                    onEvent(
                        DataFillingEvent.InputEvent(
                            InputFieldEvent.EnableErrorMessages(
                                ProfileSetupInputField.LastName
                            )
                        )
                    )
                    onEvent(DataFillingEvent.InputEvent(InputFieldEvent.Validate(inputValidator)))
                }
        )

    }
}