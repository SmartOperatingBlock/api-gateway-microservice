/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package infrastructure.api.handlers

import application.presenter.api.NumberOfZonesDto
import application.service.RoomService
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
        RoomService.GetRooms(provider.webClient).execute().onComplete {
            it.result().map { room ->
                room.zoneId.id
            }.distinct().count().run {
                routingContext.response().setStatusCode(HttpResponseStatus.OK.code()).end(
                    Json.encodeToString(NumberOfZonesDto(this)),
                )
            }
        }
    }
}
