package com.electro.fish.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.electro.fish.domain.LicenceType
import com.electro.fish.domain.License
import com.electro.fish.presentation.model.LicenseUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LicensesViewModel @Inject constructor(
    // private val navigator: LicensesNavigator
) : ViewModel() {

    private val _state = MutableStateFlow(LicensesStateImpl())
    val state: StateFlow<LicensesState> = _state.asStateFlow()


    fun expandLicense(license: License) {
        Log.d("debug", "expandLicense: $license")
        _state.update { it.copy(selectedId = license.id) }
    }

    fun collapseLicense() {
        _state.update { it.copy(selectedId = null) }
    }

    private data class LicensesStateImpl(
        val allLicenses: List<License> = listOf(
            object : License {
                override val id: Int = 1
                override val licenceType: LicenceType = LicenceType.MembershipMark
            },
            object : License {
                override val id: Int = 2
                override val licenceType: LicenceType = LicenceType.Fishing
            },
            object : License {
                override val id: Int = 3
                override val licenceType: LicenceType = LicenceType.Ticket
            }
        ),
        val selectedId: Int? = null
    ) : LicensesState {
        override val licenses =
            allLicenses.map { origin ->
                LicenseUi(
                    origin = origin,
                    isSelected = selectedId == origin.id
                )
            }.toImmutableList()
    }
}