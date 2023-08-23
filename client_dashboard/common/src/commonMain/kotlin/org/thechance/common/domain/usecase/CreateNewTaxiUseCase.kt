package org.thechance.common.domain.usecase

import org.thechance.common.domain.entity.AddTaxi
import org.thechance.common.domain.getway.IRemoteGateway

interface ICreateNewTaxiUseCase {

    suspend fun createTaxi(addTaxi: AddTaxi)

}

class CreateNewTaxiUseCase(private val fakeRemoteGateway: IRemoteGateway) : ICreateNewTaxiUseCase {

    override suspend fun createTaxi(addTaxi: AddTaxi) {
        fakeRemoteGateway.createTaxi(addTaxi)
    }

}