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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.electro.essential.validator.InputFormState
import com.electro.fish.commonandroid.image.ImagePickerHandler
import com.electro.fish.domain.validator.ProfileSetupInputField
import com.electro.fish.feature.dataFilling.presentation.R
import com.electro.fish.ui.theme.Dimens
import com.electro.fish.ui.util.extension.clickableWithoutIndication
import com.electro.fish.ui.util.extension.dashedBorder

@Composable
fun UploadMembershipMarkScreen(
    inputFormState: InputFormState,
    membershipMark: MutableState<Uri>,
    modifier: Modifier = Modifier
) {
    var isOpenedImagePicker by remember { mutableStateOf(false) }

    ImagePickerHandler(
        isOpened = isOpenedImagePicker,
        onImageSelected = {
            if(it == null) {
                membershipMark.value = Uri.EMPTY
            } else {
                membershipMark.value = it
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
        if (inputFormState.errorMessages[ProfileSetupInputField.MembershipMark] != null) {
            Text(
                text = inputFormState.errorMessages[ProfileSetupInputField.MembershipMark]!!,
                color = MaterialTheme.colorScheme.error
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f)
                .dashedBorder(
                    color = if (inputFormState.errorMessages[ProfileSetupInputField.MembershipMark] != null) {
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
                if (membershipMark.value != Uri.EMPTY) {
                    Image(
                        painter = rememberAsyncImagePainter(membershipMark.value),
                        contentDescription = null,
                        modifier = Modifier
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.AddPhotoAlternate,
                        contentDescription = null,
                        modifier = Modifier.size(48.dp)
                    )
                    Text(
                        text = stringResource(R.string.dataFilling_upload_photo_description),
                    )
                }
            }
        }
    }
}