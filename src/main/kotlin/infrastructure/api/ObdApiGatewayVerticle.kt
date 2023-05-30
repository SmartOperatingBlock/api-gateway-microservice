/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package infrastructure.api

import infrastructure.api.handlers.AuthenticationHandler
import infrastructure.api.handlers.BlockHealthProfessionalTrackingHandler
import infrastructure.api.handlers.ProcessInfoHandler
import infrastructure.api.handlers.RoomInfoHandler
import infrastructure.api.handlers.SurgeryReportInfoHandler
import infrastructure.api.handlers.SurgeryReportIntegrationHandler
import infrastructure.api.handlers.SurgeryReportsArchiveHandler
import infrastructure.api.handlers.ZoneHealthProfessionalTrackingHandler
import infrastructure.api.handlers.ZonesHandler
import infrastructure.provider.Provider
import io.vertx.core.AbstractVerticle
import io.vertx.ext.web.Router

/**
 * The Verticle for the Operating Block Dashboard Endpoint.
 */
class ObdApiGatewayVerticle(
    private val provider: Provider,
) : AbstractVerticle() {

    private val router: Router = Router.router(this.vertx)

    override fun start() {
        router.post("$endpoint/auth").handler(AuthenticationHandler(provider))
        router.get("$endpoint/zones").handler(ZonesHandler(provider))
        router.get("$endpoint/surgery-reports").handler(SurgeryReportsArchiveHandler(provider))
        router.get("$endpoint/surgery-reports/:processId").handler(SurgeryReportInfoHandler(provider))
        router.patch("$endpoint/surgery-report-integration/:processId")
            .handler(SurgeryReportIntegrationHandler(provider))
        router.get("$endpoint/room-info/:roomId").handler(RoomInfoHandler(provider))
        router.get("$endpoint/process-info").handler(ProcessInfoHandler(provider))
        router.get("$endpoint/zone-hp-tracking-info").handler(ZoneHealthProfessionalTrackingHandler(provider))
        router.get("$endpoint/block-hp-tracking-info").handler(BlockHealthProfessionalTrackingHandler(provider))

        vertx.createHttpServer()
            .requestHandler(router)
            .listen(port)
    }

    companion object {
        private const val version = "v1"
        private const val endpoint = "/api/$version/obd"
        private const val port = 3000
    }
}
