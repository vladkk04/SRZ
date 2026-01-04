package com.electro.fish.presentation.dataFilling.pager.screen

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddPhotoAlternate
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.electro.essential.validator.InputFieldEvent
import com.electro.essential.validator.InputFormState
import com.electro.fish.commonandroid.image.ImagePickerHandler
import com.electro.fish.domain.model.FishingLicenceFieldValue
import com.electro.fish.domain.validator.ProfileSetupInputField
import com.electro.fish.feature.dataFilling.presentation.R
import com.electro.fish.presentation.dataFilling.DataFillingEvent
import com.electro.fish.ui.component.AppOutlinedDatePickerTextField
import com.electro.fish.ui.theme.Dimens
import com.electro.fish.ui.util.extension.clickableWithoutIndication
import com.electro.fish.ui.util.extension.dashedBorder
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun UploadFishingLicenceScreen(
    inputValidator: FishingLicenceFieldValue,
    inputFormState: InputFormState,
    onEvent: (DataFillingEvent) -> Unit,
    modifier: Modifier = Modifier,
    fishingLicence: MutableState<Uri>,
    licenceExpireValue: MutableState<String>
) {
    var isOpenedImagePicker by remember { mutableStateOf(false) }

    ImagePickerHandler(
        isOpened = isOpenedImagePicker,
        onImageSelected = {
            if (it == null) {
                fishingLicence.value = Uri.EMPTY
            } else {
                fishingLicence.value = it
            }
            isOpenedImagePicker = false
        }
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(
            Dimens.ExtraLargePadding,
            Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        if (inputFormState.errorMessages[ProfileSetupInputField.FishingLicenseImage] != null) {
            Text(
                text = inputFormState.errorMessages[ProfileSetupInputField.FishingLicenseImage]!!,
                color = MaterialTheme.colorScheme.error
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f)
                .dashedBorder(
                    color = if (inputFormState.errorMessages[ProfileSetupInputField.FishingLicenseImage] != null) {
                        MaterialTheme.colorScheme.error
                    } else {
                        Color.White
                    },
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
                if (fishingLicence.value != Uri.EMPTY) {
                    Image(
                        painter = rememberAsyncImagePainter(fishingLicence.value),
                        contentDescription = null,
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.AddPhotoAlternate,
                        contentDescription = null,
                        modifier = Modifier.size(48.dp)
                    )
                    Text(text = stringResource(R.string.dataFilling_upload_photo_description))
                }
            }
        }

        UploadFishingDataPickerTextField(
            inputValidator = inputValidator,
            inputFormState = inputFormState,
            onEvent = onEvent,
            licenceExpire = licenceExpireValue,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun UploadFishingDataPickerTextField(
    inputValidator: FishingLicenceFieldValue,
    inputFormState: InputFormState,
    onEvent: (DataFillingEvent) -> Unit,
    licenceExpire: MutableState<String>,
    modifier: Modifier = Modifier
) {
    val focusRequester = remember { FocusRequester() }

    AppOutlinedDatePickerTextField(
        onDateSelected = {
            if (it != null) {
                val date = Date(it)
                val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                licenceExpire.value = format.format(date)
            } else {
                licenceExpire.value = ""
            }
            onEvent(DataFillingEvent.InputEvent(InputFieldEvent.Validate(inputValidator)))
        },
        isError = inputFormState.errorMessages.containsKey(ProfileSetupInputField.ExpiryDate),
        errorMessage = inputFormState.errorMessages[ProfileSetupInputField.ExpiryDate],
        label = stringResource(R.string.dataFilling_expiry_date),
        modifier = modifier.focusRequester(focusRequester)
    )
}