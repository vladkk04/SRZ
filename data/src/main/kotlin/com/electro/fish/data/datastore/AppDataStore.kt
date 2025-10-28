package com.electro.fish.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.reflect.KClass

class AppDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    suspend fun <T> set(key: String, value: T) {
        dataStore.edit { prefs ->
            when (value) {
                is Int -> prefs[intPreferencesKey(key)] = value
                is Long -> prefs[longPreferencesKey(key)] = value
                is Float -> prefs[floatPreferencesKey(key)] = value
                is String -> prefs[stringPreferencesKey(key)] = value
                is Boolean -> prefs[booleanPreferencesKey(key)] = value
                else -> throw IllegalArgumentException("Unsupported type: ${value!!::class}")
            }
        }
    }

    suspend fun <T : Any> getOrNull(key: String, clazz: KClass<T>): T? {
        val prefs = dataStore.data.first()

        @Suppress("UNCHECKED_CAST")
        return when (clazz) {
            Int::class -> prefs[intPreferencesKey(key)] as T?
            Long::class -> prefs[longPreferencesKey(key)] as T?
            Float::class -> prefs[floatPreferencesKey(key)] as T?
            String::class -> prefs[stringPreferencesKey(key)] as T?
            Boolean::class -> prefs[booleanPreferencesKey(key)] as T?
            else -> throw IllegalArgumentException("Unsupported type: $clazz")
        }
    }

    @Suppress("UNCHECKED_CAST")
    suspend fun <T : Any> get(key: String, defaultValue: T, clazz: KClass<T>): T {
        val prefs = dataStore.data.first()

        return when (clazz) {
            Int::class -> (prefs[intPreferencesKey(key)] ?: defaultValue) as T
            Long::class -> (prefs[longPreferencesKey(key)] ?: defaultValue) as T
            Float::class -> (prefs[floatPreferencesKey(key)] ?: defaultValue) as T
            String::class -> (prefs[stringPreferencesKey(key)] ?: defaultValue) as T
            Boolean::class -> (prefs[booleanPreferencesKey(key)] ?: defaultValue) as T
            else -> throw IllegalArgumentException("Unsupported type: $clazz")
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> getByFlow(key: String, defaultValue: T?, clazz: KClass<T>): Flow<T> {
        return dataStore.data.map {
            when (clazz) {
                Int::class -> (it[intPreferencesKey(key)] ?: defaultValue) as T
                Long::class -> (it[longPreferencesKey(key)] ?: defaultValue) as T
                Float::class -> (it[floatPreferencesKey(key)] ?: defaultValue) as T
                String::class -> (it[stringPreferencesKey(key)] ?: defaultValue) as T
                Boolean::class -> (it[booleanPreferencesKey(key)] ?: defaultValue) as T
                else -> throw IllegalArgumentException("Unsupported type: $clazz")
            }
        }
    }

    suspend fun <T: Any> remove(key: String, clazz: KClass<T>) {
        dataStore.edit {
            when (clazz) {
                Int::class -> it.remove(intPreferencesKey(key))
                Long::class -> it.remove(longPreferencesKey(key))
                Float::class -> it.remove(floatPreferencesKey(key))
                String::class -> it.remove(stringPreferencesKey(key))
                Boolean::class -> it.remove(booleanPreferencesKey(key))
                else -> throw IllegalArgumentException("Unsupported type: $clazz")
            }
        }
    }
}