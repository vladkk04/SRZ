package com.electro.fish.presentation

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.electro.fish.feature.profile.presentation.R
import com.electro.fish.ui.component.AppElevatedButton
import com.electro.fish.ui.component.AppIcon
import com.electro.fish.ui.theme.Dimens

data class ProfileButtonData(
    @param:StringRes val textResId: Int,
    val icon: ImageVector,
    val onClick: () -> Unit
)

@Composable
fun ProfileScreen() {

    val profileButtons = listOf(
        ProfileButtonData(R.string.profile_edit_profile, Icons.Outlined.Edit) {},
        ProfileButtonData(R.string.profile_licensees, ImageVector.vectorResource(R.drawable.license)) {},
        ProfileButtonData(R.string.profile_settings, Icons.Default.Settings) {},
        ProfileButtonData(R.string.profile_settings, Icons.Default.Settings) {},
        ProfileButtonData(R.string.profile_settings, Icons.Default.Settings) {},
        ProfileButtonData(R.string.profile_language, Icons.Default.Language) {},
        ProfileButtonData(R.string.profile_log_out, Icons.AutoMirrored.Filled.ExitToApp) {}
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.LargePadding)
        ) {
            profileButtons.forEach { data ->
                ProfileButtonItem(
                    data = data,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun ProfileButtonItem(
    data: ProfileButtonData,
    modifier: Modifier = Modifier,
) {
    AppElevatedButton(
        onClick = data.onClick,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppIcon(
                imageVector = data.icon,
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )

            Spacer(modifier = Modifier.width(ButtonDefaults.IconSpacing))

            Text(text = stringResource(id = data.textResId))
        }
    }
}