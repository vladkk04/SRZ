package com.electro.fish.domain.resources

import com.electro.essential.resources.ValidationStringProvider

interface AddCaughtFishStringProvider: ValidationStringProvider {
    val typeOfFish: String
    val lengthInCm: String
    val weightInKg: String
}