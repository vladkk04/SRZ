package com.electro.fish.presentation.container

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import com.electro.fish.ui.component.PagerScope
import com.electro.fish.ui.component.PagerScreenFeature

data class ForgotPasswordPagerScreenFeature(
    @param:StringRes val titleResId: Int,
    @param:StringRes val descriptionResId: Int,
    override val content: @Composable PagerScope.() -> Unit
) : PagerScreenFeature