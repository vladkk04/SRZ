package com.electro.fish.presentation.dataFilling.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.electro.fish.feature.dataFilling.presentation.R
import com.electro.fish.ui.component.AppElevatedButton
import com.electro.fish.ui.component.AppElevatedCard
import com.electro.fish.ui.component.AppOutlinedTextField
import com.electro.fish.ui.theme.Dimens
import com.electro.fish.ui.util.extension.onFocusLost

@Composable
fun ChangeEmailDialog(
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit,
) {
    Dialog(
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        ),
        onDismissRequest = onDismiss
    ) {
        AppElevatedCard {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(Dimens.MediumPadding),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.MediumPadding)
            ) {
                Text(
                    text = stringResource(R.string.dataFilling_oops_change_email),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.headlineSmall
                )

                Text(
                    text = stringResource(R.string.dataFilling_user_already_exist_error),
                    style = MaterialTheme.typography.titleMedium
                )

                AppOutlinedTextField(
                    onValueChange = {
                        /*emailValue.value = it
                        onEvent(
                            dataFillingEvent.InputEvent(
                                InputFieldEvent.ClearError(
                                    DefaultAuthFormInputFieldValidation.Email
                                )
                            )
                        )*/
                    },
                    label = "stringResource(R.string)",
                    isEnabled = true,
                    /* isError = inputFormState.errorMessages.containsKey(DefaultAuthFormInputFieldValidation.Email),
                     errorMessage = inputFormState.errorMessages[DefaultAuthFormInputFieldValidation.Email],*/
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    imeAction = ImeAction.Next,
                    onImeAction = {
                        /*  onEvent(
                              dataFillingEvent.InputEvent(
                                  InputFieldEvent.Validate(
                                      newAccountCredentials()
                                  )
                              )
                          )*/
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        //.focusRequester(focusRequester)
                        .onFocusLost {
                            /* onEvent(
                                 dataFillingEvent.InputEvent(
                                     InputFieldEvent.EnableErrorMessages(
                                         DefaultAuthFormInputFieldValidation.Email
                                     )
                                 )
                             )
                             onEvent(dataFillingEvent.InputEvent(InputFieldEvent.Validate(newAccountCredentials())))*/
                        }
                )

                AppElevatedButton(
                    text = stringResource(R.string.dataFilling_confirm),
                    onClick = { onConfirm("email") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

        }
    }
}