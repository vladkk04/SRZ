package com.electro.fish.presentation

import android.os.Build
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.electro.essential.model.ImageSource
import com.electro.fish.feature.profile.presentation.R
import com.electro.fish.presentation.util.openAppLanguageSettings
import com.electro.fish.ui.component.AppAsyncImage
import com.electro.fish.ui.component.AppElevatedButton
import com.electro.fish.ui.component.AppIcon
import com.electro.fish.ui.component.AppImage
import com.electro.fish.ui.theme.Dimens

data class ProfileButtonData(
    @param:StringRes val textResId: Int,
    val icon: ImageVector,
    val onClick: () -> Unit
)

@Composable
fun ProfileScreen() {
    val viewModel: ProfileViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

    val context = LocalContext.current

    val profileButtons = listOf(
        ProfileButtonData(R.string.profile_licensees, ImageVector.vectorResource(R.drawable.license)) {
            viewModel.launchLicenseesScreen()
        },
        ProfileButtonData(R.string.profile_language, Icons.Default.Language) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                context.openAppLanguageSettings()
            } else {
                viewModel.launchLanguageScreen()
            }
        },
        ProfileButtonData(R.string.profile_log_out, Icons.AutoMirrored.Filled.ExitToApp) {
            viewModel.signOut()
        }
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(Dimens.HugePadding),
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.LargePadding)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(Dimens.LargePadding),
            modifier = Modifier.fillMaxWidth()
        ) {
            AppImage(
                painter = painterResource(id = R.drawable.default_user_avatar),
                modifier = Modifier.size(96.dp)
            )
            Column {
                Text(
                    text = state.profileDataUi.firstName,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier
                )
                Text(
                    text = state.profileDataUi.lastName,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier
                )
            }

        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(Dimens.MediumPadding),
            modifier = Modifier
                .fillMaxWidth()
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