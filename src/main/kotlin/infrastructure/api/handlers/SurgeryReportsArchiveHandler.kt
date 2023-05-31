/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package infrastructure.api.handlers

import application.presenter.api.report.toSurgeryReportEntryDto
import application.service.SurgeryReportService.SurgeryReportArchiveService
import infrastructure.provider.Provider
import io.netty.handler.codec.http.HttpResponseStatus
import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * The handler for the authentication API.
 */
class SurgeryReportsArchiveHandler(
    private val provider: Provider,
) : Handler<RoutingContext> {

    override fun handle(routingContext: RoutingContext) {
        SurgeryReportArchiveService(provider.webClient).execute().onSuccess { reports ->
            reports.map { report ->
                report.toSurgeryReportEntryDto()
            }.run {
                routingContext.response().setStatusCode(HttpResponseStatus.OK.code())
                    .putHeader("content-type", "application/json")
                    .end(
                        Json.encodeToString(this),
                    )
            }
        }
    }
}
