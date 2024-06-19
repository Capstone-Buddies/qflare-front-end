package com.dicoding.capstone.data.pref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "session")
// [ Catatan ] class ini untuk mapping data user ke pref
class UserPreference private constructor(private val dataStore: DataStore<Preferences>) {
    suspend fun saveSession(user: UserModel) {
        dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = user.token
            preferences[NAME_KEY] = user.username
            preferences[EMAIL_KEY] = user.email
            preferences[SCHOOL_KEY] = user.schoolOrigin
            preferences[LEVEL_KEY] = user.level
            preferences[EXP] = user.exp
            preferences[PIC_KEY] = user.profileImgUrl
            preferences[IS_LOGIN_KEY] = true
        }
    }

    fun getSession(): Flow<UserModel> {
        return dataStore.data.map { preferences ->
            UserModel(
                preferences[TOKEN_KEY].toString(),
                preferences[NAME_KEY].toString(),
//                preferences[USER_ID].toString(),
                preferences[EMAIL_KEY].toString(),
                preferences[SCHOOL_KEY].toString(),
                preferences[LEVEL_KEY].toString(),
                preferences[EXP].toString(),
                preferences[PIC_KEY].toString(),
                preferences[IS_LOGIN_KEY] ?: false
            )
        }
    }


    suspend fun logout() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: UserPreference? = null
        private val TOKEN_KEY = stringPreferencesKey("token")
        private val NAME_KEY = stringPreferencesKey("name")
//        private val USER_ID = stringPreferencesKey("id")
        private val EMAIL_KEY = stringPreferencesKey("email")
        private val SCHOOL_KEY = stringPreferencesKey("schoolOrigin")
        private val LEVEL_KEY = stringPreferencesKey("level")
        private val EXP = stringPreferencesKey("exp")
        private val PIC_KEY = stringPreferencesKey("profileImgUrl")
        private val IS_LOGIN_KEY = booleanPreferencesKey("isLogin")

        fun getInstance(dataStore: DataStore<Preferences>): UserPreference {
            return INSTANCE ?: synchronized(this) {
                val _instance = UserPreference(dataStore)
                INSTANCE = _instance
                _instance
            }
        }
    }
}
