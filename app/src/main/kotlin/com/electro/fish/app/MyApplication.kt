package com.electro.fish.app

import android.app.Application
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.SingletonImageLoader
import coil3.disk.DiskCache
import coil3.disk.directory
import coil3.request.CachePolicy
import coil3.util.DebugLogger
import com.electro.essential.exception.mapper.DefaultExceptionToMessageMapper
import com.electro.essential.exception.mapper.ExceptionToMessageMapper
import dagger.hilt.android.HiltAndroidApp
import okio.FileSystem
import javax.inject.Inject

@HiltAndroidApp
class MyApplication: Application(), SingletonImageLoader.Factory {

    @Inject
    lateinit var exceptionToMessageMapper: ExceptionToMessageMapper

    override fun onCreate() {
        super.onCreate()
        ExceptionToMessageMapper.init(exceptionToMessageMapper)
    }

    override fun newImageLoader(context: PlatformContext): ImageLoader =
        ImageLoader(context)
            .newBuilder()
            .logger(DebugLogger())
            .diskCache {
                DiskCache.Builder()
                    .directory(context.filesDir.resolve("images"))
                    .maxSizePercent(0.1)
                    .build()
            }
            .build()
}