package com.electro.essential.resources

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StringProviderStore @Inject constructor(
    @PublishedApi
    internal val stringProvider: Map<Class<*>, @JvmSuppressWildcards StringProvider>
) {
    inline operator fun <reified T: StringProvider> invoke() : T {
        return stringProvider[T::class.java] as T
    }
}