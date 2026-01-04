package com.electro.fish.data.network.image

import android.app.appsearch.StorageInfo
import com.electro.essential.FileRepository
import com.electro.essential.TypePicture
import com.electro.essential.UrlUpload
import com.electro.fish.data.network.NetworkConfig
import io.ktor.client.HttpClient
import io.ktor.client.request.forms.InputProvider
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.utils.io.streams.asInput
import java.io.File
import javax.inject.Inject

class FileRepositoryImpl @Inject constructor(
    private val ktorClient: HttpClient
) : FileRepository {
    override suspend fun uploadImages(url: UrlUpload, vararg uploads: Pair<TypePicture, File?>) {
        try {
            val url = when (url) {
                UrlUpload.Profile -> NetworkConfig.UPLOAD_PICTURE_PATH_URL
                is UrlUpload.AddFish -> NetworkConfig.addFishPicture(url.fishId)
            }

             ktorClient.post(url) {
                 setBody(
                     MultiPartFormDataContent(
                         formData {
                             uploads.forEach { (type, file) ->
                                 if (file != null && file.exists()) {
                                     append(
                                         key = type.data,
                                         value = InputProvider { file.inputStream().asInput() },
                                         headers = Headers.build {
                                             append(HttpHeaders.ContentType, "image/jpg")
                                             append(HttpHeaders.ContentDisposition, "filename=\"${file.name}\"")
                                         }
                                     )
                                 }
                             }
                         }
                     )
                 )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}