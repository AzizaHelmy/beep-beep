package org.thechance.common.data.local.gateway

import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import org.thechance.common.data.local.localDto.KeepUserLoggedInLocalDto
import org.thechance.common.data.local.localDto.TokenLocalDto
import org.thechance.common.data.local.localDto.TokenType
import org.thechance.common.domain.getway.ILocalDataGateway
import java.io.File


class LocalDataGateway(private val realm: Realm) : ILocalDataGateway {


    override suspend fun saveTaxiReport(file: File) {
        //todo save file
    }

    override suspend fun saveAccessToken(token: String) {
        realm.write {
            copyToRealm(TokenLocalDto().apply {
                id = ACCESS_TOKEN_ID
                this.token = token
                this.type = TokenType.ACCESS_TOKEN.name
            })
        }
    }

    override suspend fun saveRefreshToken(token: String) {
        realm.write {
            copyToRealm(TokenLocalDto().apply {
                id = REFRESH_TOKEN_ID
                this.token = token
                this.type = TokenType.REFRESH_TOKEN.name
            })
        }
    }

    override suspend fun getAccessToken(): String {
        return realm.query<TokenLocalDto>(
            "$TOKEN_TYPE == ${TokenType.ACCESS_TOKEN.name} AND $TOKEN_ID == $ACCESS_TOKEN_ID",
        ).first().find()?.token!!
    }

    override suspend fun getRefreshToken(): String {
        return realm.query<TokenLocalDto>(
            "$TOKEN_TYPE == ${TokenType.REFRESH_TOKEN.name} AND $TOKEN_ID == $REFRESH_TOKEN_ID",
        ).first().find()?.token!!
    }

    override suspend fun clearTokens() {
        realm.write { delete(query<TokenLocalDto>()) }
    }

    override suspend fun shouldUserKeptLoggedIn(keepLoggedIn: Boolean) {
        realm.write { KeepUserLoggedInLocalDto().apply {
            id = KEEP_USER_LOGGED_IN_ID
            this.keepLoggedIn = keepLoggedIn }
        }
    }

    override suspend fun isUserKeptLoggedIn(): Boolean {
        return realm.query<KeepUserLoggedInLocalDto>(
            "$LOGIN_FLAG_ID == $KEEP_USER_LOGGED_IN_ID"
        ).first().find()?.keepLoggedIn ?: false
    }

    companion object{
        const val TOKEN_ID = "id"
        const val LOGIN_FLAG_ID = "id"
        const val TOKEN_TYPE = "type"
        const val REFRESH_TOKEN_ID = 0
        const val ACCESS_TOKEN_ID = 1
        const val KEEP_USER_LOGGED_IN_ID = 0
    }

}