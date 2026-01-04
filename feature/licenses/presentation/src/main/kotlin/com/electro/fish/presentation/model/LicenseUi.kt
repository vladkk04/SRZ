package com.electro.fish.presentation.model

import androidx.compose.runtime.Immutable
import com.electro.fish.domain.License

@Immutable
data class LicenseUi(
    val origin: License,
    val isSelected: Boolean = false
): License by origin
