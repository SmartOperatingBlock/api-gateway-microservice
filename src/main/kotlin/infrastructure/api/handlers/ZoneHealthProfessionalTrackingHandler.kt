/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package infrastructure.api.handlers

import application.presenter.api.room.RoomsId
import application.presenter.api.tracking.toTrackingInfoDto
import application.service.TrackingService
import entity.room.RoomData
import infrastructure.provider.Provider
import io.vertx.core.CompositeFuture
import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * The handler for health professional tracking API.
 */
class ZoneHealthProfessionalTrackingHandler(
    private val provider: Provider,
) : Handler<RoutingContext> {

    override fun handle(routingContext: RoutingContext) {
        routingContext.request().body().onSuccess {
            val operatingRoomId = Json.decodeFromString<RoomsId>(it.toString()).operatingRoomId
            val preOperatingRoomId = Json.decodeFromString<RoomsId>(it.toString()).preOperatingRoomId
            val result = TrackingService.ZoneHealthProfessionalTrackingInfo(
                RoomData.RoomId(preOperatingRoomId),
                RoomData.RoomId(operatingRoomId),
                provider.webClient,
            ).execute()

            CompositeFuture.all(result.first, result.second).onSuccess {
                CompositeFuture.all(result.first.result()).onSuccess {
                    CompositeFuture.all(result.second.result()).onSuccess {
                        val operating = result.first.result().map { hp ->
                            hp.result().toTrackingInfoDto()
                        }
                        val preOperating = result.second.result().map { hp ->
                            hp.result().toTrackingInfoDto()
                        }
                        routingContext.response()
                            .putHeader("content-type", "application/json")
                            .end(Json.encodeToString(operating + preOperating))
                    }
                }
            }
        }
    }
}
