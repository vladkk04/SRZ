package com.electro.fish.presentation.dataFilling

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.electro.essential.validator.InputFieldEvent
import com.electro.essential.validator.ValidationResult
import com.electro.essential.validator.WithFormInputValidator
import com.electro.essential.validator.validate
import com.electro.fish.domain.model.AddressInputFieldValue
import com.electro.fish.domain.model.FishingLicenceFieldValue
import com.electro.fish.domain.model.FishingTicketFieldValue
import com.electro.fish.domain.model.FullNameInputFieldValue
import com.electro.fish.domain.model.MembershipMarkFieldValue
import com.electro.fish.domain.model.NewAccountFull
import com.electro.fish.domain.model.PhoneNumberInputFieldValue
import com.electro.fish.feature.dataFilling.presentation.R
import com.electro.fish.presentation.dataFilling.pager.container.DataFillingContentContainer
import com.electro.fish.presentation.dataFilling.pager.container.DataFillingScreenPagerFeature
import com.electro.fish.presentation.dataFilling.pager.screen.AddressScreen
import com.electro.fish.presentation.dataFilling.pager.screen.BirthdayScreen
import com.electro.fish.presentation.dataFilling.pager.screen.FullNameScreen
import com.electro.fish.presentation.dataFilling.pager.screen.PhoneNumberScreen
import com.electro.fish.presentation.dataFilling.pager.screen.UploadFishingLicenceScreen
import com.electro.fish.presentation.dataFilling.pager.screen.UploadFishingTicketScreen
import com.electro.fish.presentation.dataFilling.pager.screen.UploadMembershipMarkScreen
import com.electro.fish.ui.component.AppAnimatedLinearProgressIndicator
import com.electro.fish.ui.component.AppElevatedLoadingButton
import com.electro.fish.ui.component.AppHorizontalPager
import com.electro.fish.ui.component.AppIconButton
import com.electro.fish.ui.component.PagerScope
import com.electro.fish.ui.theme.Dimens
import kotlinx.collections.immutable.persistentListOf

@Composable
fun DataFillingScreen() {
    val viewModel = hiltViewModel<DataFillingViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current

    val firstName = rememberSaveable { mutableStateOf("") }
    val lastName = rememberSaveable { mutableStateOf("") }
    val phoneNumber = rememberSaveable { mutableStateOf("") }
    val birthday = rememberSaveable { mutableStateOf("") }
    val licenceExpire = rememberSaveable { mutableStateOf("") }
    val address = rememberSaveable { mutableStateOf("") }

    val fishingLicence = rememberSaveable { mutableStateOf(Uri.EMPTY) }
    val fishingTicket  = rememberSaveable { mutableStateOf(Uri.EMPTY) }
    val membershipMark = rememberSaveable { mutableStateOf(Uri.EMPTY) }

    val fullNameValidator = remember(firstName.value, lastName.value) {
        FullNameInputFieldValue(firstName.value, lastName.value)
    }

    val phoneNumberValidator = remember(phoneNumber.value) {
        PhoneNumberInputFieldValue(phoneNumber.value)
    }

    val addressValidator = remember(address.value) {
        AddressInputFieldValue(address.value)
    }

    val fishingLicenceFieldValue = remember(fishingLicence.value, licenceExpire.value) {
        FishingLicenceFieldValue(fishingLicence.value, licenceExpire.value)
    }

    val fishingTickerFieldValue = remember(fishingTicket.value) {
        FishingTicketFieldValue(fishingTicket.value)
    }

    val membershipMarkFieldValue = remember(membershipMark.value) {
        MembershipMarkFieldValue(membershipMark.value)
    }


    fun getValidatorForPage(page: Int): WithFormInputValidator? {
        return when (page) {
            0 -> fullNameValidator
            1 -> null
            2 -> phoneNumberValidator
            3 -> addressValidator
            4 -> fishingLicenceFieldValue
            5 -> fishingTickerFieldValue
            6 -> membershipMarkFieldValue
            else -> null
        }
    }

    val newAccount = {
        NewAccountFull(
            firstName = firstName.value,
            lastName = lastName.value,
            phoneNumber = phoneNumber.value,
            birthDate = birthday.value,
            address = address.value,
            licenseExpiryDate = licenceExpire.value,
            fishingLicence = fishingLicence.value,
            fishingTicket = fishingTicket.value,
            membershipMark = membershipMark.value
        )
    }

    val pagerScreens = persistentListOf(
        DataFillingScreenPagerFeature(
            titleResId = R.string.dataFilling_title_profile_setup_whats_your_name,
            content = {
                FullNameScreen(
                    inputValidator = fullNameValidator,
                    inputFormState = state.inputFormState,
                    onEvent = viewModel::onEvent,
                    firstName = firstName,
                    lastName = lastName,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        ),
        DataFillingScreenPagerFeature(
            titleResId = R.string.dataFilling_title_profile_setup_whats_your_birthday,
            content = {
                BirthdayScreen(
                    onDateSelected = {
                        birthday.value = it
                    }
                )
            }
        ),
        DataFillingScreenPagerFeature(
            titleResId = R.string.dataFilling_title_profile_setup_whats_your_phone_number,
            content = {
                PhoneNumberScreen(
                    inputValidator = phoneNumberValidator,
                    inputFormState = state.inputFormState,
                    onEvent = viewModel::onEvent,
                    phoneNumber = phoneNumber,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        ),
        DataFillingScreenPagerFeature(
            titleResId = R.string.dataFilling_title_profile_setup_whats_your_address,
            content = {
                AddressScreen(
                    inputValidator = addressValidator,
                    inputFormState = state.inputFormState,
                    onEvent = viewModel::onEvent,
                    addressValue = address,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        ),
        DataFillingScreenPagerFeature(
            titleResId = R.string.dataFilling_title_profile_setup_upload_your_fishing_license,
            content = {
                UploadFishingLicenceScreen(
                    inputValidator = fishingLicenceFieldValue,
                    inputFormState = state.inputFormState,
                    onEvent = viewModel::onEvent,
                    fishingLicence = fishingLicence,
                    licenceExpireValue = licenceExpire
                )
            }
        ),
        DataFillingScreenPagerFeature(
            titleResId = R.string.dataFilling_title_profile_setup_upload_your_fishing_ticket,
            content = {
                UploadFishingTicketScreen(
                    inputFormState = state.inputFormState,
                    fishingTicket = fishingTicket,
                )
            }
        ),
        DataFillingScreenPagerFeature(
            titleResId = R.string.dataFilling_title_profile_setup_upload_your_membership_mark,
            content = {
                UploadMembershipMarkScreen(
                    inputFormState = state.inputFormState,
                    membershipMark = membershipMark
                )
            }
        )
    )

  /*  ChangeEmailDialog(
        onDismiss = { },
        onConfirm = {

        }
    )*/


    AppHorizontalPager(
        screens = pagerScreens,
        userScrollEnabled = false,
        titleContainer = {
            it.PagerProgressBar(
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .align(Alignment.Start)
            )
        },
        contentContainer = DataFillingContentContainer(
            modifier = Modifier.padding(horizontal = Dimens.ExtraLargePadding)
        ),
        bottomContainer = {
            val currentPageIndex = it.getCurrentPage()

            it.ProfileSetupPagerButton(
                isLoading = state.isDataFillingInProgress,
                onFinish = { viewModel.finish(newAccount()) },
                onNext = {
                    val validator = getValidatorForPage(currentPageIndex)

                    if (validator != null) {
                        val validationResult = validator.validate()

                        if (validationResult is ValidationResult.Success) {
                            it.requestNext()
                        } else {
                            viewModel.onEvent(DataFillingEvent.InputEvent(InputFieldEvent.Validate(validator)))

                            validator.toFieldValues().forEach { fieldVal ->
                                viewModel.onEvent(
                                    DataFillingEvent.InputEvent(
                                        InputFieldEvent.EnableErrorMessages(fieldVal.inputField)
                                    )
                                )
                            }
                        }
                    } else {
                        it.requestNext()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(bottom = Dimens.MediumPadding)
            )
        }
    )
}

@Composable
private fun PagerScope.ProfileSetupPagerButton(
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    onFinish: () -> Unit = {},
    onNext: () -> Unit = {}
) {
    val isLastPage = getCurrentPage() == pageCount - 1

    AppElevatedLoadingButton(
        text = stringResource(if (isLastPage) R.string.dataFilling_finish else R.string.dataFilling_continue),
        isLoading = isLoading,
        onClick = { if (isLastPage) onFinish() else onNext() },
        modifier = modifier
    )
}

@Composable
private fun PagerScope.PagerProgressBar(
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(Dimens.SmallPadding),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        AppIconButton(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            isEnabled = this@PagerProgressBar.getCurrentPage() != 0,
            onClick = this@PagerProgressBar::requestPrevious
        )
        AppAnimatedLinearProgressIndicator(
            progress = (this@PagerProgressBar.getCurrentPage().toFloat() / this@PagerProgressBar.pageCount),
            modifier = Modifier.fillMaxWidth()
        )
    }
}
