@file:OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)

package com.electro.fish.presentation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeGesturesPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dangerous
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.electro.fish.feature.fishingSession.session.presentation.R
import com.electro.fish.ui.component.AppIcon
import com.electro.fish.ui.layout.AppSplitElevatedButtonLayoutWithDropdownMenu

@Composable
fun FishingSessionScreen() {
    val context = LocalContext.current

    val viewModel = hiltViewModel<FishingSessionViewModel>()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .safeGesturesPadding()
    ) {
        AppSplitElevatedButtonLayoutWithDropdownMenu(
            leadingButtonText = stringResource(R.string.session_add_fish),
            trailingButtonText = stringResource(R.string.session_stop_fishing),
            onLeadingButtonClick = { viewModel.launchAddCaughtFishScreen() },
            onTrailingButtonClick = {  },
            leadingIcon = {
                AppIcon(drawableResId = R.drawable.hook)
            },
            trailingIcon = {
                AppIcon(imageVector = Icons.Default.Dangerous)
            },
            colorsTrailingButton = ButtonDefaults.elevatedButtonColors().copy(
                containerColor = Color(0xFFF13636)
            ),
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }




   /* val intent = Intent(context.applicationContext, FishingSessionService::class.java).also {
        it.action = FishingSessionAction.START.name
    }

    context.startForegroundService(intent)*/

    /*var selectedBitmap by remember { mutableStateOf<Bitmap?>(null) }
    var resultText by remember { mutableStateOf<String?>(null) }


    val imageLabeler: ImageLabeler = remember {
        val localModel = LocalModel.Builder()
            .setAssetFilePath("model.tflite")
            .build()
        val options = CustomImageLabelerOptions.Builder(localModel)
            .setConfidenceThreshold(0.3f)
            .setMaxResultCount(1)
            .build()
        ImageLabeling.getClient(options)
    }

    val pickImageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        uri?.let {
            val bitmap = if (Build.VERSION.SDK_INT < 28) {
                @Suppress("DEPRECATION")
                MediaStore.Images.Media.getBitmap(context.contentResolver, it)
            } else {
                val source = ImageDecoder.createSource(context.contentResolver, it)
                ImageDecoder.decodeBitmap(source)
            }
            selectedBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true)
            resultText = "Processing..."
            runClassification(imageLabeler, selectedBitmap!!) { text ->
                resultText = text
            }
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            selectedBitmap?.let {
                Image(
                    bitmap = it.asImageBitmap(),
                    contentDescription = "Selected photo",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
            } ?: Box(modifier = Modifier.fillMaxWidth().height(308.dp))

            resultText?.let { Text(it) }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { pickImageLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Load Photo")
            }
        }
    }*/

    //FishingSessionScreenContent()
}

/*private fun runClassification(
    imageLabeler: ImageLabeler,
    bitmap: Bitmap,
    onResult: (String) -> Unit
) {
    val image = InputImage.fromBitmap(bitmap, 0)
    imageLabeler.process(image)
        .addOnSuccessListener { labels ->
            if (labels.isEmpty()) {
                onResult("Could not recognize the fish.")
                return@addOnSuccessListener
            }
            val firstLabel = labels[0]
            val mappedName = when (firstLabel.text) {

                else -> firstLabel.text
            }
            onResult("Recognized: $mappedName (Confidence: ${String.format("%.1f", firstLabel.confidence * 100)}%)")
        }
        .addOnFailureListener { e ->
            onResult("Error: ${e.localizedMessage}")
        }
}*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FishingSessionScreenContent() {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {

    }
}