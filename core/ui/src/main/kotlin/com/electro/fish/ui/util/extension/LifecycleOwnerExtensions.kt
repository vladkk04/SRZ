package com.electro.fish.ui.util.extension

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope

suspend fun LifecycleOwner.repeatWithLifecycleResumed(
    block: suspend CoroutineScope.() -> Unit
) = this.lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED, block)

