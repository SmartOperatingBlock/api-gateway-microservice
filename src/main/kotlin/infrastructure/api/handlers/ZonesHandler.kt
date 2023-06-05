/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package infrastructure.api.handlers

import application.presenter.api.room.ZoneInfoDto
import application.service.RoomService
import entity.room.RoomData
import infrastructure.provider.Provider
import io.netty.handler.codec.http.HttpResponseStatus
import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * The Handler for the number of zones API.
 */
class ZonesHandler(
    private val provider: Provider,
) : Handler<RoutingContext> {

    override fun handle(routingContext: RoutingContext) {
        RoomService.GetRooms(provider.webClient).execute().onSuccess { rooms ->
            rooms.map {
                    room ->
                room.zoneId.id
            }.distinct().toList().map { zoneId ->
                ZoneInfoDto(
                    zoneId,
                    rooms.firstOrNull {
                        it.zoneId.id == zoneId && it.type == RoomData.RoomType.PRE_OPERATING_ROOM
                    }?.id?.id,
                    rooms.firstOrNull {
                        it.zoneId.id == zoneId && it.type == RoomData.RoomType.OPERATING_ROOM
                    }?.id?.id,
                )
            }.run {
                routingContext.response()
                    .setStatusCode(
                        if (this.isNotEmpty()) HttpResponseStatus.OK.code() else HttpResponseStatus.NO_CONTENT.code(),
                    )
                    .putHeader("content-type", "application/json")
                    .end(
                        Json.encodeToString(this),
                    )
            }
        }
    }
}
