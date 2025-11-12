package com.electro.presentation.profileSetup.viewpagerScreen

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddPhotoAlternate
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.electro.fish.feature.signUp.presentation.R
import com.electro.fish.ui.component.AppOutlinedTextField
import com.electro.fish.ui.component.FocusManagerAction
import com.electro.fish.ui.theme.Dimens
import com.electro.fish.ui.util.extension.dashedBorder

@Composable
fun ProfileSetupLicenceScreen(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(Dimens.ExtraLargePadding, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f)
                .dashedBorder(
                    color = Color.White,
                    shape = RoundedCornerShape(12.dp),
                    dashLength = 12.dp,
                    gapLength = 8.dp
                )
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(Dimens.SmallPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(
                    imageVector = Icons.Default.AddPhotoAlternate,
                    contentDescription = null,
                    modifier = Modifier.size(48.dp)
                )
                Text(
                    text = stringResource(R.string.signUp_upload_photo_description),
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(Dimens.SmallPadding),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            HorizontalDivider(modifier.fillMaxWidth(0.4f))
            Text(
                text = stringResource(R.string.signUp_or),
                modifier = Modifier.padding(horizontal = Dimens.ExtraSmallPadding)
            )
            HorizontalDivider()
        }

        AppOutlinedTextField(
            onValueChange = { },
            label = stringResource(R.string.signUp_licence_number),
            focusManagerAction = FocusManagerAction.Done(),
            modifier = Modifier.fillMaxWidth()
        )
    }


}