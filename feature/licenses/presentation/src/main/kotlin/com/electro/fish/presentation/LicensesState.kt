package com.electro.fish.presentation

import androidx.compose.runtime.Immutable
import com.electro.fish.domain.LicenceType
import com.electro.fish.domain.License
import com.electro.fish.presentation.model.LicenseUi
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.ImmutableSet
import kotlinx.collections.immutable.persistentSetOf

@Immutable
interface LicensesState {
    val licenses: ImmutableList<LicenseUi>
}
