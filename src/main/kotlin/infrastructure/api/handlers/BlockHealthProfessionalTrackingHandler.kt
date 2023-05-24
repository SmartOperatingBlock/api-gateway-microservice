/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package infrastructure.api.handlers

import application.presenter.api.tracking.toTrackingInfoDto
import application.service.TrackingService
import infrastructure.provider.Provider
import io.vertx.core.CompositeFuture
import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * The handler for Process Info API.
 */
class BlockHealthProfessionalTrackingHandler(
    private val provider: Provider,
) : Handler<RoutingContext> {

    override fun handle(routingContext: RoutingContext) {
        val result = TrackingService.BlockHealthProfessionalTrackingInfo(
            provider.webClient,
        ).execute()
        result.onSuccess { list ->
            CompositeFuture.all(list).onSuccess {
                val block = list.map { hp ->
                    hp.result().toTrackingInfoDto()
                }
                routingContext.response().end(Json.encodeToString(block))
            }
        }
    }
}
