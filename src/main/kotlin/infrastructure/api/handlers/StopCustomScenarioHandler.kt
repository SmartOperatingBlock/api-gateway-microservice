/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package infrastructure.api.handlers

import application.presenter.event.ProcessEvent
import application.presenter.event.payload.ProcessEventPayloads
import io.netty.handler.codec.http.HttpResponseStatus
import io.vertx.core.Handler
import io.vertx.core.Vertx
import io.vertx.ext.web.RoutingContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.time.Instant

/**
 * The Handler for stop custom automation scenario events.
 */
class StopCustomScenarioHandler(private val vertx: Vertx) : Handler<RoutingContext> {

    override fun handle(routingContext: RoutingContext) {
        routingContext.queryParam("roomId").firstOrNull()?.let { roomId ->
            val event = ProcessEvent(
                key = "CUSTOM_LIGHT_STOP_REQUEST_EVENT",
                dateTime = Instant.now().toString(),
                data = ProcessEventPayloads.StopCustomLightEvent(roomId),
            )
            vertx.eventBus().send(
                "stop-custom-light-events",
                Json.encodeToString(event),
            )
            routingContext.response().setStatusCode(HttpResponseStatus.NO_CONTENT.code()).end()
        }
    }
}
