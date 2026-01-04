package com.electro.fish.presentation

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeGesturesPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.electro.fish.feature.fishingSession.selectFishingSpot.presentation.R
import com.electro.fish.presentation.component.FishingSpotItem
import com.electro.fish.presentation.model.FishingSpotItemData
import com.electro.fish.ui.component.AppSearchBar
import com.electro.fish.ui.theme.Dimens
import java.io.IOException

@Composable
fun SelectFishingSpotScreen() {
    val viewModel: SelectFishingSpotViewModel = hiltViewModel()

    val context = LocalContext.current
    var fishingSpots by remember { mutableStateOf(readFishingSpotsFromRaw(context, R.raw.result)) }

    Box {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(Dimens.SmallPadding),
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = Dimens.MediumPadding)
        ) {
            items(fishingSpots) { data ->
                FishingSpotItem(
                    fishingSpotItemData = data,
                    onClick = { viewModel.launchFishingSessionScreen(data.spotCode) }
                )
            }
        }

        AppSearchBar(
            rememberTextFieldState(),
            onSearch = {},
            searchResults = listOf(),
            modifier = Modifier
                .safeGesturesPadding()
                .align(Alignment.BottomCenter)
        )
    }
}

fun readFishingSpotsFromRaw(context: Context, resourceId: Int): List<FishingSpotItemData> {
    return try {
        val inputStream = context.resources.openRawResource(resourceId)

        val fileContent = inputStream.bufferedReader().use { it.readText() }

        fileContent
            .lines()
            .filter { it.isNotBlank() }
            .map { line ->
                val parts = line.trim().split(Regex("\\s+"), limit = 2)

                val code = parts.getOrNull(0) ?: ""
                val name = parts.getOrNull(1) ?: ""

                FishingSpotItemData(name, code)
            }
    } catch (e: IOException) {
        e.printStackTrace()
        emptyList()
    }
}