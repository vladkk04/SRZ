package com.electro.fish.presentation

import android.R.attr.text
import android.location.Address
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddPhotoAlternate
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.rememberAsyncImagePainter
import com.electro.essential.validator.InputFieldEvent
import com.electro.fish.feature.fishingSession.addCaughtFish.presentation.R
import com.electro.fish.ui.component.AppButton
import com.electro.fish.ui.component.AppIcon
import com.electro.fish.ui.component.AppOutlinedExposedDropdownMenuTextField
import com.electro.fish.ui.component.AppOutlinedTextField
import com.electro.fish.ui.theme.Dimens
import com.electro.fish.ui.util.extension.clickableWithoutIndication
import com.electro.fish.ui.util.extension.dashedBorder
import com.electro.fish.commonandroid.image.ImagePickerHandler
import com.electro.fish.data.fishingSession.model.FishType
import com.electro.fish.domain.model.AddCaughtFishCredentials
import com.electro.fish.domain.validator.AddCaughtFishInputFieldValidation
import com.electro.fish.ui.component.AppImage
import com.electro.fish.ui.component.AppLoadingButton
import com.electro.fish.ui.util.extension.onFocusLost
import io.ktor.util.collections.getValue
import io.ktor.util.collections.setValue


@Composable
fun AddCaughtFishScreen() {

    val viewModel = hiltViewModel<AddCaughtFishViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    AddCaughtFishContent(
        state,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun AddCaughtFishContent(
    state: AddCaughtFishState,
    onEvent: (AddCaughtFishEvent) -> Unit,
) {

    var isOpenedImagePicker by remember { mutableStateOf(false) }

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var typeOfFish by remember { mutableStateOf("") }
    var lengthInCm by remember { mutableStateOf("") }
    var weightInKg by remember { mutableStateOf("") }

    val addCaughtFishCredentials = {
        AddCaughtFishCredentials(
            image = imageUri ?: Uri.EMPTY,
            typeOfFish = typeOfFish,
            length = lengthInCm,
            weight = weightInKg
        )
    }

    ImagePickerHandler(
        isOpened = isOpenedImagePicker,
        onImageSelected = {
            imageUri = it
            isOpenedImagePicker = false
        }
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(Dimens.MediumPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(Dimens.SmallPadding)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f)
                .dashedBorder(
                    color = Color.White,
                    shape = RoundedCornerShape(12.dp),
                    dashLength = 12.dp,
                    gapLength = 8.dp
                )
                .clickableWithoutIndication { isOpenedImagePicker = true }
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(Dimens.SmallPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                if (imageUri == null) {
                    Icon(
                        imageVector = Icons.Default.AddPhotoAlternate,
                        contentDescription = null,
                        modifier = Modifier.size(48.dp)
                    )
                    Text(text = stringResource(R.string.addCaughtFish_upload_fish_picture))
                } else {
                    AppImage(
                        painter = rememberAsyncImagePainter(imageUri),
                    )
                }
            }
        }

        AppOutlinedExposedDropdownMenuTextField(
            label = stringResource(R.string.addCaughtFish_type_of_fish),
            items = FishType.entries.map { it.name },
            selectedValue = typeOfFish,
            onItemSelected = { typeOfFish = it },
            leadingIcon = {
                BadgedBox(
                    badge = {
                        Badge {
                            Text(
                                text = stringResource(R.string.addCaughtFish_ai),
                                style = MaterialTheme.typography.labelSmall,
                            )
                        }
                    },
                    modifier = Modifier
                        .clickable {
                        }
                ) {
                    AppIcon(imageVector = Icons.Default.AutoAwesome)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                space = Dimens.SmallPadding,
                alignment = Alignment.CenterHorizontally
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            AppOutlinedTextField(
                onValueChange = { lengthInCm = it },
                leadingIcon = { AppIcon(drawableResId = R.drawable.ic_ruler) },
                label = stringResource(R.string.addCaughtFish_cm),
                isError = state.inputFormState.errorMessages.containsKey(
                    AddCaughtFishInputFieldValidation.LengthInCm
                ),
                errorMessage = state.inputFormState.errorMessages[AddCaughtFishInputFieldValidation.LengthInCm],
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                imeAction = ImeAction.Done,
                onImeAction = {
                    onEvent(
                        AddCaughtFishEvent.InputEvent(
                            InputFieldEvent.Validate(
                                addCaughtFishCredentials()
                            )
                        )
                    )
                },
                modifier = Modifier
                    .weight(0.5f)
                    .onFocusLost {
                        onEvent(
                            AddCaughtFishEvent.InputEvent(
                                InputFieldEvent.EnableErrorMessages(
                                    AddCaughtFishInputFieldValidation.LengthInCm
                                )
                            )
                        )
                        onEvent(
                            AddCaughtFishEvent.InputEvent(
                                InputFieldEvent.Validate(
                                    addCaughtFishCredentials()
                                )
                            )
                        )
                    }
            )

            AppOutlinedTextField(
                onValueChange = { weightInKg = it },
                leadingIcon = { AppIcon(drawableResId = R.drawable.ic_weight) },
                label = stringResource(R.string.addCaughtFish_kg),
                isError = state.inputFormState.errorMessages.containsKey(
                    AddCaughtFishInputFieldValidation.WeightInKg
                ),
                errorMessage = state.inputFormState.errorMessages[AddCaughtFishInputFieldValidation.WeightInKg],
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                imeAction = ImeAction.Done,
                onImeAction = {
                    onEvent(
                        AddCaughtFishEvent.InputEvent(
                            InputFieldEvent.Validate(
                                addCaughtFishCredentials()
                            )
                        )
                    )
                },
                modifier = Modifier
                    .weight(0.5f)
                    .onFocusLost {
                        onEvent(
                            AddCaughtFishEvent.InputEvent(
                                InputFieldEvent.EnableErrorMessages(
                                    AddCaughtFishInputFieldValidation.WeightInKg
                                )
                            )
                        )
                        onEvent(
                            AddCaughtFishEvent.InputEvent(
                                InputFieldEvent.Validate(
                                    addCaughtFishCredentials()
                                )
                            )
                        )
                    }
            )
        }

        AppLoadingButton(
            text = stringResource(R.string.addCaughtFish_add_fish),
            isLoading = state.isAddCaughtFishInProgress,
            isEnabled = addCaughtFishCredentials().typeOfFish.isNotEmpty() &&
                    addCaughtFishCredentials().length.isNotEmpty() &&
                    addCaughtFishCredentials().weight.isNotEmpty(),
            onClick = { onEvent(AddCaughtFishEvent.AddCaughtFish(addCaughtFishCredentials())) },
            modifier = Modifier.fillMaxWidth()
        )
    }
}