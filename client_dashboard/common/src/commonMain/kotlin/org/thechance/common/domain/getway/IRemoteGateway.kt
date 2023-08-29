package org.thechance.common.domain.getway

import org.thechance.common.domain.entity.*


interface IRemoteGateway {

    fun getUserData(): String

    fun getUsers(page: Int, numberOfUsers: Int): DataWrapper<User>

    suspend fun getTaxis(page: Int, numberOfUsers: Int): DataWrapper<Taxi>

    suspend fun createTaxi(taxi: NewTaxiInfo): Taxi

    suspend fun findTaxisByUsername(username: String, page: Int, offset:Int): DataWrapper<Taxi>

    suspend fun getPdfTaxiReport()

    suspend fun getRestaurants(
        pageNumber: Int,
        numberOfRestaurantsInPage: Int,
        restaurantName: String,
        rating: Double?,
        priceLevel: Int?
    ): DataWrapper<Restaurant>

    suspend fun loginUser(username: String, password: String): Pair<String, String>

    suspend fun createRestaurant(restaurant: AddRestaurant): Restaurant

    suspend fun getCurrentLocation(): Location

}
