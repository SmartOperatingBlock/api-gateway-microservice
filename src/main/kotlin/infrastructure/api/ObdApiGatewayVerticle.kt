/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package infrastructure.api

import infrastructure.api.handlers.AuthenticationHandler
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

        vertx.createHttpServer()
            .requestHandler(router)
            .listen(port)
    }

    companion object {
        private const val version = "v1"
        private const val endpoint = "/api/$version/obd"
        private const val port = 3002
    }
}
