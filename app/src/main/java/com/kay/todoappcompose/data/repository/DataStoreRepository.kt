package com.kay.todoappcompose.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.kay.todoappcompose.data.models.Priority
import com.kay.todoappcompose.util.Constants.PREFERENCE_KEY
import com.kay.todoappcompose.util.Constants.PREFERENCE_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_NAME)

@ViewModelScoped /* Need viewModelScoped to inject this data store repository inside our sharedViewModel */
class DataStoreRepository @Inject constructor(
    // We are going to need context in dataStore Repository
    @ApplicationContext private val context: Context
) {
    // Specify the actual key for our sort state using our constant value
    private object PreferenceKeys {
        val sortKey = stringPreferencesKey(name = PREFERENCE_KEY)
    }

    private val dataStore = context.dataStore

    // function for saving or persisting that sort state
    suspend fun persistSortState(priority: Priority) {
        dataStore.edit { preference ->
            preference[PreferenceKeys.sortKey] = priority.name
        }
    }

    val readSortState: Flow<String> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val sortState = preferences[PreferenceKeys.sortKey] ?: Priority.NONE.name
            sortState
        }
}
