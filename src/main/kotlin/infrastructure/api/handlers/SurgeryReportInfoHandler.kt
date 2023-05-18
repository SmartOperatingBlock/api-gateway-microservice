/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package infrastructure.api.handlers

import application.presenter.api.toSurgeryReportInfoDto
import application.service.SurgeryReportInfoService
import infrastructure.provider.Provider
import io.netty.handler.codec.http.HttpResponseStatus
import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * The handler for the authentication API.
 */
class SurgeryReportInfoHandler(
    private val provider: Provider,
) : Handler<RoutingContext> {

    override fun handle(routingContext: RoutingContext) {
        SurgeryReportInfoService(routingContext.pathParam("processId"), provider.webClient).execute().onComplete {
            routingContext
                .response()
                .setStatusCode(HttpResponseStatus.OK.code())
                .end(
                    Json.encodeToString(
                        it.map { sr ->
                            sr.toSurgeryReportInfoDto()
                        }.result(),
                    ),
                )
        }
    }
}
