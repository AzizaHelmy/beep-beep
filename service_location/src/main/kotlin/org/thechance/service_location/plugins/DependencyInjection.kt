package org.thechance.service_location.plugins

import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin
import org.thechance.service_location.di.BeepClient

fun Application.configureDependencyInjection() {
    install(Koin) {
        modules(BeepClient)
    }
}