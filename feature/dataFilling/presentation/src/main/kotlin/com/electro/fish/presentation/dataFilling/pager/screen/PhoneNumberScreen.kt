package com.electro.fish.presentation.dataFilling.pager.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.electro.essential.validator.InputFieldEvent
import com.electro.essential.validator.InputFormState
import com.electro.fish.domain.model.PhoneNumberInputFieldValue
import com.electro.fish.domain.validator.ProfileSetupInputField
import com.electro.fish.feature.dataFilling.presentation.R
import com.electro.fish.presentation.dataFilling.DataFillingEvent
import com.electro.fish.ui.component.AppOutlinedTextField
import com.electro.fish.ui.component.PagerScope
import com.electro.fish.ui.theme.Dimens
import com.electro.fish.ui.util.extension.onFocusLost

@Composable
fun PagerScope.PhoneNumberScreen(
    inputValidator: PhoneNumberInputFieldValue,
    inputFormState: InputFormState,
    onEvent: (DataFillingEvent) -> Unit,
    phoneNumber: MutableState<String>,
    modifier: Modifier = Modifier
) {
    val focusRequester = remember { FocusRequester() }

    AppOutlinedTextField(
        onValueChange = { phoneNumber.value = it },
        leadingIcon = {
            Text(
                text = "+421",
                modifier = Modifier.padding(start = Dimens.MediumPadding),
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        label = stringResource(R.string.dataFilling_phone_number),
        isError = inputFormState.errorMessages.containsKey(ProfileSetupInputField.PhoneNumber),
        errorMessage = inputFormState.errorMessages[ProfileSetupInputField.PhoneNumber],
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
                            ProfileSetupInputField.PhoneNumber
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