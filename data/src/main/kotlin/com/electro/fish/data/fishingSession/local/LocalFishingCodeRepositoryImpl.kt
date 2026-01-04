package com.electro.fish.data.fishingSession.local

import com.electro.fish.data.datastore.AppDataStore
import com.electro.fish.data.fishingSession.LocalFishingCodeProvider
import com.electro.fish.data.fishingSession.LocalFishingCodeSaver
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalFishingCodeRepositoryImpl @Inject constructor(
    private val dataSaver: AppDataStore
): LocalFishingCodeSaver, LocalFishingCodeProvider {

    private val fishingCodeKey = "fishingCodeKey"

    override suspend fun saveCode(code: String) {
        dataSaver.set(fishingCodeKey, code)
    }

    override suspend fun getCode(): String {
        return dataSaver.getOrNull(fishingCodeKey, String::class) ?: ""
    }

    override fun getCodeByFlow(): Flow<String> {
        return dataSaver.getByFlow(fishingCodeKey, "", String::class)
    }

}