package com.electro.fish.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.electro.fish.feature.fishingSession.selectFishingSpot.presentation.R
import com.electro.fish.presentation.model.FishingSpotItemData
import com.electro.fish.ui.component.SmallVerticalSpacer
import com.electro.fish.ui.theme.Dimens
import com.electro.fish.ui.util.extension.clickableWithoutIndication

@Composable
fun FishingSpotItem(
    fishingSpotItemData: FishingSpotItemData,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .clickableWithoutIndication(onClick)
    ) {
        Column(
            modifier = Modifier.padding(Dimens.MediumPadding)
        ) {
            Text(
                text = stringResource(R.string.selectFishingSpot_spot_item_title, fishingSpotItemData.spotName),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium
            )

            SmallVerticalSpacer()

            Text(
                text = stringResource(R.string.selectFishingSpot_spot_item_code, fishingSpotItemData.spotCode),
                color = Color.White,
                style = MaterialTheme.typography.titleSmall
            )
        }

    }


}