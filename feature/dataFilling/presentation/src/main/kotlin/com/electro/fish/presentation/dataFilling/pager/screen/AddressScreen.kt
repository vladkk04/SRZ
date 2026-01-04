package com.electro.fish.presentation.dataFilling.pager.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.electro.essential.validator.InputFieldEvent
import com.electro.essential.validator.InputFormState
import com.electro.fish.domain.model.AddressInputFieldValue
import com.electro.fish.domain.validator.ProfileSetupInputField
import com.electro.fish.feature.dataFilling.presentation.R
import com.electro.fish.presentation.dataFilling.DataFillingEvent
import com.electro.fish.ui.component.AppOutlinedTextField
import com.electro.fish.ui.component.PagerScope
import com.electro.fish.ui.util.extension.onFocusLost

@Composable
fun PagerScope.AddressScreen(
    inputValidator: AddressInputFieldValue,
    inputFormState: InputFormState,
    onEvent: (DataFillingEvent) -> Unit,
    addressValue: MutableState<String>,
    modifier: Modifier = Modifier
) {
    val focusRequester = remember { FocusRequester() }

    AppOutlinedTextField(
        onValueChange = { addressValue.value = it },
        label = stringResource(R.string.dataFilling_address),
        isError = inputFormState.errorMessages.containsKey(ProfileSetupInputField.Address),
        errorMessage = inputFormState.errorMessages[ProfileSetupInputField.Address],
        imeAction = ImeAction.Done,
        onImeAction = {
            onEvent(
                DataFillingEvent.InputEvent(
                    InputFieldEvent.Validate(inputValidator)
                )
            )
        },
        modifier = modifier
            .focusRequester(focusRequester)
            .onFocusLost {
                onEvent(
                    DataFillingEvent.InputEvent(
                        InputFieldEvent.EnableErrorMessages(
                            ProfileSetupInputField.Address
                        )
                    )
                )
                onEvent(
                    DataFillingEvent.InputEvent(
                        InputFieldEvent.Validate(inputValidator)
                    )
                )
            }
    )

}