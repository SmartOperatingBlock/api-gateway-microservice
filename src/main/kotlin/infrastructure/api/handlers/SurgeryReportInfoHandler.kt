/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package infrastructure.api.handlers

import application.presenter.serialization.SurgeryReportSerializer.toApiDto
import application.service.SurgeryReportService.SurgeryReportInfoService
import infrastructure.provider.Provider
import io.netty.handler.codec.http.HttpResponseStatus
import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement

/**
 * The handler for the authentication API.
 */
class SurgeryReportInfoHandler(
    private val provider: Provider,
) : Handler<RoutingContext> {

    override fun handle(routingContext: RoutingContext) {
        SurgeryReportInfoService(
            routingContext.pathParam("processId"),
            provider.webClient,
        ).execute().onSuccess { report ->
            routingContext
                .response()
                .setStatusCode(HttpResponseStatus.OK.code())
                .putHeader("content-type", "application/json")
                .end(
                    Json.encodeToJsonElement(report.toApiDto()).toString(),
                )
        }
    }
}
