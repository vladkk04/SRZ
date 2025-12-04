package com.electro.fish.presentation

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.electro.fish.feature.selectFishingSpot.presentation.R
import java.io.IOException

@Composable
fun SelectFishingSpotScreen() {
    val context = LocalContext.current
    var fishingSpots by remember { mutableStateOf(emptyList<String>()) }

    LaunchedEffect(Unit) {
        // Pass the resource ID of your raw file here
        val loadedSpots = readFishingSpotsFromRaw(context, R.raw.result)
        fishingSpots = loadedSpots
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(fishingSpots) { spot ->
            Text(
                text = spot,
            )
        }
    }

}

fun readFishingSpotsFromRaw(context: Context, resourceId: Int): List<String> {
    return try {
        // 1. Open the file using its Resource ID (R.raw.fishing_spots)
        val inputStream = context.resources.openRawResource(resourceId)

        // 2. Read the whole file content into a String
        val fileContent = inputStream.bufferedReader().use { it.readText() }

        // 3. Split the string into a list of items (one item per line)
        fileContent.split('\n')
            .map { it.trim() } // Remove leading/trailing whitespace
            .filter { it.isNotEmpty() } // Remove any empty lines
    } catch (e: IOException) {
        e.printStackTrace()
        // Return an empty list or throw a custom exception on failure
        emptyList()
    }
}