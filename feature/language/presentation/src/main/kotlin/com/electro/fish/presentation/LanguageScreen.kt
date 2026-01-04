package com.electro.fish.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.electro.fish.domain.model.AppLanguage
import com.electro.fish.feature.language.presentation.R
import com.electro.fish.ui.component.AppElevatedCard
import com.electro.fish.ui.component.RadioButtonSingleSelection
import com.electro.fish.ui.theme.Dimens

@Composable
fun LanguageScreen() {
    val viewModel: LanguageViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

    AppElevatedCard {
        Text(
            text = stringResource(R.string.language_select_language),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .padding(Dimens.LargePadding)
                .align(Alignment.CenterHorizontally)
        )

        RadioButtonSingleSelection(
            items = AppLanguage.entries,
            selectedItem = state.currentLanguage,
            onSelectionChanged = { viewModel.changeLanguage(it) },
            itemLabel = { it.name }
        )
    }
}