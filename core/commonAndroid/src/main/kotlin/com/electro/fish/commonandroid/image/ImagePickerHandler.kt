package com.electro.fish.commonandroid.image

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.LaunchedEffect

@Composable
fun ImagePickerHandler(
    isOpened: Boolean = false,
    onImageSelected: (Uri?) -> Unit
) {
    val pickMedia =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            onImageSelected(uri)
        }

    LaunchedEffect(isOpened) {
        if (isOpened) {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
    }
}