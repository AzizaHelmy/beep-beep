package org.thechance.common.presentation.taxi

import org.thechance.common.domain.entity.Taxi

fun Taxi.toUiState(): TaxiDetailsUiState = TaxiDetailsUiState(
    id = id,
    plateNumber = plateNumber,
    color = color,
    type = type,
    seats = seats,
    username = username,
    status = status,
    trips = trips,
)

fun List<Taxi>.toUiState() = map { it.toUiState() }

fun TaxiDetailsUiState.toEntity(): Taxi = Taxi(
    id = id,
    plateNumber = plateNumber,
    color = color,
    type = type,
    seats = seats,
    username = username,
    status = status,
    trips = trips,
)

fun List<TaxiDetailsUiState>.toEntity() = map { it.toEntity() }