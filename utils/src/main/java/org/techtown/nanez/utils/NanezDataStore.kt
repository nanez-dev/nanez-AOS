package org.techtown.nanez.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

/**
 * Created by iseungjun on 2023/08/17
 */
class NanezDataStore {

    private object LazyHolder {
        val INSTANCE = NanezDataStore()
    }

    companion object {
        @JvmStatic
        fun getInstance() : NanezDataStore {
            return LazyHolder.INSTANCE
        }

        private const val DATA_STORE_NAME: String = "dataStore"
    }

    private val Context.dataStore by preferencesDataStore(DATA_STORE_NAME)

    private lateinit var dataStore: DataStore<Preferences>

    fun init(context: Context) {
        this.dataStore = context.dataStore
    }

    fun <T> getValue(key: String, defaultValueType: ValueType<T>): Flow<T> {
        return dataStore.data
            .catch {
                emit(emptyPreferences())
            }
            .map { preferences ->
                val value = when (defaultValueType) {
                    is ValueType.BooleanValue -> preferences[booleanPreferencesKey(key)]
                    is ValueType.DoubleValue -> preferences[doublePreferencesKey(key)]
                    is ValueType.FloatValue -> preferences[floatPreferencesKey(key)]
                    is ValueType.IntValue -> preferences[intPreferencesKey(key)]
                    is ValueType.LongValue -> preferences[longPreferencesKey(key)]
                    is ValueType.StringValue -> preferences[stringPreferencesKey(key)]
                    is ValueType.StringSetValue -> preferences[stringSetPreferencesKey(key)]
                }
                (value ?: defaultValueType.getProperty()) as T
            }
    }

    suspend fun <T> setValue(key: String, valueType: ValueType<T>) {
        try {
            dataStore.edit { preferences ->
                when (valueType) {
                    is ValueType.BooleanValue -> preferences[booleanPreferencesKey(key)] = valueType.value
                    is ValueType.DoubleValue -> preferences[doublePreferencesKey(key)] = valueType.value
                    is ValueType.FloatValue -> preferences[floatPreferencesKey(key)] = valueType.value
                    is ValueType.IntValue -> preferences[intPreferencesKey(key)] = valueType.value
                    is ValueType.LongValue -> preferences[longPreferencesKey(key)] = valueType.value
                    is ValueType.StringValue -> preferences[stringPreferencesKey(key)] = valueType.value
                    is ValueType.StringSetValue -> preferences[stringSetPreferencesKey(key)] = valueType.value
                }
            }
        } catch (e: Exception) {
            NaneLog.e(e)
        }
    }

    ////////////////////////////////////
    sealed class ValueType<T> {
        abstract fun getProperty(): T

        data class StringSetValue(val value: Set<String>) : ValueType<Set<String>>() {
            override fun getProperty(): Set<String> = value
        }

        data class StringValue(val value: String) : ValueType<String>() {
            override fun getProperty(): String = value
        }

        data class IntValue(val value: Int) : ValueType<Int>() {
            override fun getProperty(): Int = value
        }

        data class FloatValue(val value: Float) : ValueType<Float>() {
            override fun getProperty(): Float = value
        }

        data class DoubleValue(val value: Double) : ValueType<Double>() {
            override fun getProperty(): Double = value
        }

        data class LongValue(val value: Long) : ValueType<Long>() {
            override fun getProperty(): Long = value
        }

        data class BooleanValue(val value: Boolean) : ValueType<Boolean>() {
            override fun getProperty(): Boolean = value
        }
    }
}