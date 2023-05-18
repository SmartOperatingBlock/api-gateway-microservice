/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package infrastructure.api.handlers

import application.presenter.api.SurgeryReportIntegrationDto
import application.presenter.api.toSurgeryReportIntegration
import infrastructure.provider.Provider
import io.netty.handler.codec.http.HttpResponseStatus
import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext
import kotlinx.serialization.json.Json
import usecase.SurgeryReportIntegrationUseCase

/**
 * The handler for the surgery report integration.
 */
class SurgeryReportIntegrationHandler(
    private val provider: Provider,
) : Handler<RoutingContext> {

    override fun handle(routingContext: RoutingContext) {
        routingContext.request().body().onComplete {
            val integration = Json.decodeFromString<SurgeryReportIntegrationDto>(it.result().toString())
                .toSurgeryReportIntegration()
            SurgeryReportIntegrationUseCase(integration, provider.webClient).execute().onComplete { ar ->
                routingContext
                    .response()
                    .setStatusCode(
                        if (ar.result()) HttpResponseStatus.OK.code() else HttpResponseStatus.BAD_REQUEST.code(),
                    ).end()
            }
        }
    }
}
