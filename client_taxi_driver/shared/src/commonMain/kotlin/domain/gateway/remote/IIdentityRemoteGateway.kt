package domain.gateway.remote

import domain.entity.Session


interface IIdentityRemoteGateway {

    suspend fun loginUser(userName: String, password: String): Session

    // the pair this fun return is <accessToken, refreshToken>
    suspend fun refreshAccessToken(refreshToken: String): Pair<String, String>

    suspend fun createRequestPermission(
        taxiRequestPermission: String,
        driverEmail: String,
        description: String,
    ): Boolean

    suspend fun getTaxiDriverName(): String
}

