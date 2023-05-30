/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package infrastructure.api

import application.presenter.event.ProcessEvent
import application.presenter.event.payload.ProcessEventPayloads.MedicalTechnologyAutomationProposalEvent
import infrastructure.api.handlers.AdaptEnvironmentHandler
import infrastructure.api.handlers.CustomScenarioHandler
import infrastructure.api.handlers.ProcessManualEventHandler
import infrastructure.api.handlers.ProcessStateHandler
import infrastructure.api.handlers.RoomInfoHandler
import infrastructure.api.handlers.StopCustomScenarioHandler
import infrastructure.event.util.EventProperties.Topics
import infrastructure.provider.Provider
import io.vertx.core.AbstractVerticle
import io.vertx.core.http.ServerWebSocket
import io.vertx.ext.web.Router
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * The Verticle for the Operating Room Dashboard Endpoint.
 */
class OrdApiGatewayVerticle(
    private val provider: Provider,
) : AbstractVerticle() {

    private val router = Router.router(this.vertx)
    private val webSockets: MutableList<ServerWebSocket> = mutableListOf()

    override fun start() {
        router.get("$endpoint/room-info/:roomId").handler(RoomInfoHandler(provider))
        router.get("$endpoint/process-state").handler(ProcessStateHandler(provider))
        router.post("$endpoint/process-manual-events").handler(ProcessManualEventHandler(this.vertx))
        router.post("$endpoint/custom-automation-scenario").handler(CustomScenarioHandler(this.vertx))
        router.post("$endpoint/stop-custom-automation-scenario").handler(StopCustomScenarioHandler(this.vertx))
        router.post("$endpoint/adapt-environment").handler(AdaptEnvironmentHandler(this.vertx))

        router.get("socket-connection").handler {
            it.request().toWebSocket().onSuccess { webSocket ->
                webSocket.accept()
                webSockets.add(webSocket)
            }
        }

        this.vertx.eventBus().consumer(Topics.automationProposalsEventsTopic) { message ->
            val event = Json.decodeFromString<ProcessEvent<MedicalTechnologyAutomationProposalEvent>>(message.body())
            webSockets.firstOrNull()?.writeTextMessage(Json.encodeToString(event.data))
        }

        vertx.createHttpServer()
            .requestHandler(router)
            .listen(port)
    }

    companion object {
        private const val version = "v1"
        private const val endpoint = "/api/$version/ord"
        private const val port = 3000
    }
}
