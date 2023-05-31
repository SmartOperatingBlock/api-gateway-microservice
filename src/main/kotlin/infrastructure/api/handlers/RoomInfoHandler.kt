/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package infrastructure.api.handlers

import application.presenter.api.room.RoomSerialization.toRoomDto
import application.service.RoomService
import entity.room.RoomData
import infrastructure.provider.Provider
import io.netty.handler.codec.http.HttpResponseStatus
import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * The Handler for the information about a Room API.
 */
class RoomInfoHandler(
    private val provider: Provider,
) : Handler<RoutingContext> {

    override fun handle(routingContext: RoutingContext) {
        routingContext.pathParam("roomId")?.let {
            RoomService.GetRoomEnvironmentalInfo(
                RoomData.RoomId(it),
                provider.webClient,
            ).execute().onSuccess { room ->
                routingContext.response()
                    .setStatusCode(HttpResponseStatus.OK.code())
                    .putHeader("content-type", "application/json")
                    .end(
                        Json.encodeToString(room.toRoomDto()),
                    )
            }
        }
    }
}
