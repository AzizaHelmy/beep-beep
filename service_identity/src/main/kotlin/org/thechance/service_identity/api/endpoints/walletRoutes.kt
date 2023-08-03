package org.thechance.service_identity.api.endpoints

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import org.thechance.service_identity.api.model.WalletDto
import org.thechance.service_identity.domain.usecases.wallet.WalletUseCaseContainer
import org.thechance.service_identity.domain.entity.Wallet

fun Route.walletRoute(){

    val walletUseCaseContainer : WalletUseCaseContainer by inject()


    route("/wallet"){
        get("/{id}") {
            try {
                val id = call.parameters["id"]!!
                val wallet = walletUseCaseContainer.getWalletUseCase(id).toDto()
                call.respond(wallet)
            }catch (e: Exception){
                call.respond(HttpStatusCode.NotFound)
            }
        }
        get("/user/{id}") {
            try {
                val id = call.parameters["id"]!!
                val wallet = walletUseCaseContainer.getUserWalletUseCase(id).toDto()
                call.respond(wallet)
            }catch (e: Exception){
                call.respond(HttpStatusCode.NotFound)
            }
        }
        post{
            try {
                val wallet = call.receive<WalletDto>()
                val result = walletUseCaseContainer.addWalletUseCase(wallet.toEntity())
                call.respond(result)
            }catch (e: Exception){
                call.respond(HttpStatusCode.BadRequest)
            }
        }
        put("/{id}") {
            try {
                val id = call.parameters["id"]!!
                val wallet = call.receive<WalletDto>()
                val result = walletUseCaseContainer.updateWalletUseCase(id,wallet.toEntity())
                call.respond(result)
            }catch (e: Exception){
                call.respond(HttpStatusCode.NotFound)
            }
        }
        delete("/{id}") {
            try {
                val id = call.parameters["id"]!!
                val result = walletUseCaseContainer.deleteWalletUseCase(id)
                call.respond(result)
            }catch (e: Exception){
                call.respond(HttpStatusCode.NotFound)
            }
        }
    }

}