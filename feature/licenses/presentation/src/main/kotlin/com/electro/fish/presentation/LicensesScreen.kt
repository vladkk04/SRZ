@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.electro.fish.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.electro.fish.feature.licenses.presentation.R
import com.electro.fish.presentation.model.LicenseUi
import com.electro.fish.ui.theme.Dimens
import com.electro.fish.ui.util.extension.clickableWithoutIndication

@Composable
fun LicensesScreen() {
    val viewModel = hiltViewModel<LicensesViewModel>()
    val state by viewModel.state.collectAsState()

    BackHandler(
        enabled = state.licenses.any { it.isSelected },
        onBack = { viewModel.collapseLicense() }
    )

    SharedTransitionLayout(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            val isDetailVisible = state.licenses.any { it.isSelected }

            val backgroundBlur by animateDpAsState(targetValue = if (isDetailVisible) 4.dp else 0.dp,)

            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(Dimens.MediumPadding),
                contentPadding = PaddingValues(Dimens.MediumPadding),
                modifier = Modifier.fillMaxSize()
            ) {
                stickyHeader {
                    AnimatedVisibility(
                        visible = !isDetailVisible,
                        enter = fadeIn() + scaleIn(),
                        exit = fadeOut() + scaleOut()
                    ) {
                        Text(
                            text = stringResource(R.string.license_your_licenses),
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.displayMedium,
                            modifier = Modifier
                                .sharedBounds(
                                    sharedContentState = rememberSharedContentState(key = "header_title"),
                                    animatedVisibilityScope = this
                                )
                        )
                    }
                }
                items(state.licenses) { license ->
                    AnimatedVisibility(
                        visible = !license.isSelected,
                        enter = fadeIn() + scaleIn(),
                        exit = fadeOut() + scaleOut(),
                        modifier = Modifier.animateItem()
                    ) {
                        Card(
                            modifier = Modifier
                                .sharedBounds(
                                    sharedContentState = rememberSharedContentState(key = license.id),
                                    animatedVisibilityScope = this,
                                    clipInOverlayDuringTransition = OverlayClip(CardDefaults.shape)
                                )
                                .blur(backgroundBlur, BlurredEdgeTreatment.Unbounded)
                        ) {
                            LicenseImageCard(
                                licenseUi = license,
                                onClick = { viewModel.expandLicense(license) },
                                modifier = Modifier
                                    .sharedElement(
                                        sharedContentState = rememberSharedContentState(key = license.licenceType.name),
                                        animatedVisibilityScope = this@AnimatedVisibility
                                    )
                            )
                        }
                    }
                }
            }

            SnackEditDetails(
                licenseUi = state.licenses.firstOrNull { it.isSelected },
                onDismiss = { viewModel.collapseLicense() }
            )
        }
    }
}

@Composable
fun SharedTransitionScope.SnackEditDetails(
    licenseUi: LicenseUi?,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit
) {
    AnimatedContent(
        modifier = modifier,
        targetState = licenseUi,
        transitionSpec = {
            fadeIn() togetherWith fadeOut()
        },
        label = "SnackEditDetails"
    ) { licence ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            if (licence == null) return@AnimatedContent

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickableWithoutIndication(onDismiss)
                    .background(Color.Black.copy(alpha = 0.5f))
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(Dimens.MediumPadding),
            ) {
                Text(
                    text = licence.licenceType.name,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier
                        .sharedBounds(
                            sharedContentState = rememberSharedContentState(key = "header_title"),
                            animatedVisibilityScope = this@AnimatedContent
                        )
                )
                Card(
                    modifier = Modifier
                        .sharedBounds(
                            sharedContentState = rememberSharedContentState(key = licence.id),
                            animatedVisibilityScope = this@AnimatedContent,
                            clipInOverlayDuringTransition = OverlayClip(CardDefaults.shape)
                        )
                ) {
                    LicenseImageCard(
                        licenseUi = licence,
                        onClick = onDismiss,
                        modifier = Modifier.sharedElement(
                            sharedContentState = rememberSharedContentState(key = licence.licenceType.name),
                            animatedVisibilityScope = this@AnimatedContent,
                        )
                    )
                }
            }

        }
    }
}

@Composable
fun LicenseImageCard(
    licenseUi: LicenseUi,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier.clickableWithoutIndication(onClick)
    ) {
        Image(
            painter = painterResource(id = R.drawable.test),
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
    }
}