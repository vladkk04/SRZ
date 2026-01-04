package com.electro.fish.commonandroid.image

import android.content.Context
import android.net.Uri
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class UriConverter @Inject constructor(
    @param:ApplicationContext private val context: Context
) {
    fun toFile(uri: Uri, fileName: String): File? {
        if (uri == Uri.EMPTY) return null

        return try {
            val contentResolver = context.contentResolver

            val finalFileName = if (fileName.endsWith(".jpg")) fileName else "$fileName.jpg"

            val file = File(context.filesDir, finalFileName)

            val inputStream = contentResolver.openInputStream(uri) ?: return null

            inputStream.use { input ->
                FileOutputStream(file).use { output ->
                    input.copyTo(output)
                }
            }

            file
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}