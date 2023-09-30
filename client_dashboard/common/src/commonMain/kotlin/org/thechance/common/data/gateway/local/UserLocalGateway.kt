package org.thechance.common.data.gateway.local

//import io.realm.kotlin.Realm
//import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
//import org.thechance.common.data.gateway.local.model.ConfigurationCollection
import org.thechance.common.domain.getway.IUserLocalGateway

class UserLocalGateway() : IUserLocalGateway {

    override suspend fun createUserConfiguration() {
//        realm.write { copyToRealm(ConfigurationCollection().apply { id = CONFIGURATION_ID }) }
    }

    override suspend fun refreshAccessToken(refreshToken: String): Pair<String, String> {
        return Pair("", "")
    }

    override suspend fun saveAccessToken(token: String) {
//        realm.write {
//            query<ConfigurationCollection>("$ID == $CONFIGURATION_ID").first().find()?.accessToken = token
//        }
    }
    override suspend fun saveUserName(username: String) {
//        realm.write {
//            query<ConfigurationCollection>("$ID == $CONFIGURATION_ID").first().find()?.username = username
//        }
    }

    override suspend fun saveCountryCode(languageCode: String) {
//        realm.write {
//            query<ConfigurationCollection>("$ID == $CONFIGURATION_ID").first().find()?.languageCode = languageCode
//        }
    }

    override suspend fun getCountryCode(): String {
//        return realm.query<ConfigurationCollection>("$ID == $CONFIGURATION_ID").first().find()?.languageCode ?: ""
        return ""
    }


    override suspend fun getUserName(): String {
//        return realm.query<ConfigurationCollection>("$ID == $CONFIGURATION_ID").first().find()?.username ?: ""
        return ""
    }


    override suspend fun saveRefreshToken(token: String) {
//        realm.write {
//            query<ConfigurationCollection>("$ID == $CONFIGURATION_ID").first().find()?.refreshToken = token
//        }
    }

    override suspend fun getAccessToken(): String {
//        return realm.query<ConfigurationCollection>("$ID == $CONFIGURATION_ID").first().find()?.accessToken ?: ""
        return ""
    }

    override suspend fun getRefreshToken(): String {
//        return realm.query<ConfigurationCollection>("$ID == $CONFIGURATION_ID").first().find()?.refreshToken ?: ""
        return ""
    }

    override suspend fun clearConfiguration() {
//        realm.write { delete(query<ConfigurationCollection>()) }
    }

    override suspend fun getThemeMode(): Flow<Boolean> {
//        return realm.query<ConfigurationCollection>(
//            "$ID == $CONFIGURATION_ID"
//        ).asFlow().map { result -> result.list.find { it.isDarkMode }?.isDarkMode ?: false }
        return flow { false }
    }

    override suspend fun updateThemeMode(mode: Boolean) {
//        realm.write {
//            query<ConfigurationCollection>("$ID == $CONFIGURATION_ID").first().find()?.isDarkMode = mode
//        }
    }


    companion object {
        private const val CONFIGURATION_ID = 0
        private const val ID = "id"
    }

}