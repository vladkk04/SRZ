package com.electro.essential

import java.io.File

enum class TypePicture(val data: String) {
    FISHING_LICENSE_PICTURE("fishingLicensePhoto"),
    FISHING_TICKET_PICTURE("fishingTicketPhoto"),
    MEMBERSHIP_PICTURE("membershipMarkPhoto"),
    CAUGHT_FISH_PICTURE("file")
}

sealed interface UrlUpload {
    data object Profile: UrlUpload
    data class AddFish(val fishId: Int): UrlUpload
}

interface FileRepository {
    suspend fun uploadImages(url: UrlUpload, vararg uploads: Pair<TypePicture, File?>)
}