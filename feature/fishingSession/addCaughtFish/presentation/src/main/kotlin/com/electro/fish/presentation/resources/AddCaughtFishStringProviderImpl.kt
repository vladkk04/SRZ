package com.electro.fish.presentation.resources

import android.content.Context
import com.electro.fish.commonandroid.resource.validation.ValidationStringProviderImpl
import com.electro.fish.domain.resources.AddCaughtFishStringProvider
import com.electro.fish.feature.fishingSession.addCaughtFish.presentation.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AddCaughtFishStringProviderImpl @Inject constructor(
    @param:ApplicationContext private val context: Context
): ValidationStringProviderImpl(context), AddCaughtFishStringProvider {
    override val lengthInCm: String = context.getString(R.string.addCaughtFish_cm)
    override val weightInKg: String = context.getString(R.string.addCaughtFish_kg)
    override val typeOfFish: String = context.getString(R.string.addCaughtFish_type_of_fish)
}