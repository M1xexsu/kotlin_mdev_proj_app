package github.m1xexsu.kotlin_mdev_proj_app.data.remote


import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.tokendatastore: DataStore<Preferences> by preferencesDataStore(name = "token_datastore")

class TokenManager(private val context: Context) {

    private val access_token = stringPreferencesKey("access_token")
    private val refresh_token = stringPreferencesKey("refresh_token")

    suspend fun saveAccessToken(token: String) {
        context.tokendatastore.edit { it[access_token] = token }
    }

    suspend fun saveRefreshToken(token: String) {
        context.tokendatastore.edit { it[refresh_token] = token }
    }

    val accessTokenFlow: Flow<String?> = context.tokendatastore.data.map { it[access_token] }
    val refreshTokenFlow: Flow<String?> = context.tokendatastore.data.map { it[refresh_token] }

    suspend fun clearTokens() {
        context.tokendatastore.edit {
            it.remove(access_token)
            it.remove(refresh_token)
        }

    }
}