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
 * The Handler for process manual events.
 */
class ProcessManualEventHandler(private val vertx: Vertx) : Handler<RoutingContext> {

    override fun handle(routingContext: RoutingContext) {
        routingContext.queryParam("roomId").firstOrNull()?.let { roomId ->
            routingContext.queryParam("event").firstOrNull()?.let { processEvent ->
                val event = ProcessEvent(
                    key = "PROCESS_MANUAL_EVENT",
                    dateTime = Instant.now().toString(),
                    data = ProcessEventPayloads.ProcessManualEvent(
                        roomId,
                        processEvent,
                    ),
                )
                vertx.eventBus().send(
                    "process-manual-events",
                    Json.encodeToString(event),
                )
                routingContext.response().setStatusCode(HttpResponseStatus.OK.code()).end()
            }
        }
    }
}
