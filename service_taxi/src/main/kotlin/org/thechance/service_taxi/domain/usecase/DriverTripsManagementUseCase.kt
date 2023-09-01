package org.thechance.service_taxi.domain.usecase

import org.thechance.service_taxi.domain.entity.Trip
import org.thechance.service_taxi.domain.exceptions.ResourceNotFoundException
import org.thechance.service_taxi.domain.gateway.ITaxiGateway

interface IDriverTripsManagementUseCase {
    suspend fun getTripsByDriverId(driverId: String, page: Int, limit: Int): List<Trip> // driver
    suspend fun approveTrip(driverId: String, taxiId: String, tripId: String): Trip // driver
    suspend fun finishTrip(driverId: String, tripId: String): Trip // driver
    suspend fun getNumberOfTripsByDriverId(id: String): Long
}

class DriverTripsManagementUseCase(
    private val ITaxiGateway: ITaxiGateway,
) : IDriverTripsManagementUseCase {
    override suspend fun approveTrip(driverId: String, taxiId: String, tripId: String): Trip {
        ITaxiGateway.getTripById(tripId) ?: throw ResourceNotFoundException
        return ITaxiGateway.approveTrip(tripId, taxiId, driverId) ?: throw ResourceNotFoundException
    }

    override suspend fun finishTrip(driverId: String, tripId: String): Trip {
        ITaxiGateway.getTripById(tripId) ?: throw ResourceNotFoundException
        return ITaxiGateway.finishTrip(tripId, driverId) ?: throw ResourceNotFoundException
    }

    override suspend fun getNumberOfTripsByDriverId(id: String): Long {
        return ITaxiGateway.getNumberOfTripsByDriverId(id)
    }

    override suspend fun getTripsByDriverId(
        driverId: String,
        page: Int,
        limit: Int
    ): List<Trip> {
        return ITaxiGateway.getDriverTripsHistory(driverId, page, limit)
    }


}