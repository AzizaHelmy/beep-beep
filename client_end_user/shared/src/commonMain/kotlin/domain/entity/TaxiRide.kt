package domain.entity

data class TaxiRide(
    val id: String,
    val taxiPlateNumber: String,
    val taxiDriverName: String,
    val driverImage: String,
    val carType: String,
    val startPoint: Location,
    val destination: Location,
    val startPointAddress: String,
    val destinationAddress: String,
    val rate: Double,
    val tripStatus: Trip.TripStatus,
)