package com.electro.essential.extension

import kotlin.reflect.KClass

fun (() -> Unit).catch(vararg exceptions: KClass<out Throwable>, catchBlock: (Throwable) -> Unit) {
    try {
        this()
    } catch (e: Throwable) {
        if (e::class in exceptions) catchBlock(e) else throw e
    }
}