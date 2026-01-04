package com.electro.fish.presentation.dataFilling.pager.container

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import com.electro.fish.ui.component.PagerScope
import com.electro.fish.ui.component.PagerScreenFeature

data class DataFillingScreenPagerFeature(
    @param:StringRes val titleResId: Int,
    override val content: @Composable PagerScope.() -> Unit
) : PagerScreenFeature