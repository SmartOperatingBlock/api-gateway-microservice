/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package infrastructure.api.handlers

import application.presenter.api.process.toSurgicalProcessDto
import application.presenter.api.room.RoomsInZoneDto
import application.service.ProcessService
import entity.room.RoomData
import infrastructure.provider.Provider
import io.netty.handler.codec.http.HttpResponseStatus
import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * The handler for Process Info API.
 */
class ProcessInfoHandler(
    private val provider: Provider,
) : Handler<RoutingContext> {

    override fun handle(routingContext: RoutingContext) {
        routingContext.request().body().onSuccess {
            Json.decodeFromString<RoomsInZoneDto>(it.toString()).run {
                ProcessService.GetProcessInfoByRoomId(
                    RoomData.RoomId(this.preOperatingRoomId),
                    RoomData.RoomId(this.operatingRoomId),
                    provider.webClient,
                ).execute().onSuccess { surgicalProcess ->
                    if (surgicalProcess != null) {
                        routingContext.response().setStatusCode(HttpResponseStatus.OK.code())
                            .putHeader("content-type", "application/json")
                            .end(Json.encodeToString(surgicalProcess.toSurgicalProcessDto()))
                    } else {
                        routingContext.response().setStatusCode(HttpResponseStatus.NO_CONTENT.code()).end()
                    }
                }
            }
        }
    }
}
