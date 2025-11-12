package com.electro.commonandroid.dialog

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.window.Dialog
import com.electro.essential.DialogExceptionHandler
import com.electro.essential.exception.mapper.ExceptionToMessageMapper
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class DialogExceptionHandlerImpl @Inject constructor(
    private val exceptionToMessageMapper: ExceptionToMessageMapper
): DialogExceptionHandler {
    private var errorMessageState = mutableStateOf<String?>(null)

    override fun handleException(exception: Exception) {
        errorMessageState.value = exceptionToMessageMapper.getLocalizedMessage(exception)
    }

    @Composable
    fun ErrorDialog() {
        errorMessageState.value?.let { message ->
            Dialog(
                onDismissRequest = { errorMessageState.value = null },
                content = {
                    Text(
                        text = message
                    )
                }
            )
        }
    }
}