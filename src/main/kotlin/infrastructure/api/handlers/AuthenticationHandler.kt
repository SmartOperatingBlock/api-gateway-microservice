/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package infrastructure.api.handlers

import application.presenter.api.Login
import infrastructure.provider.Provider
import io.netty.handler.codec.http.HttpResponseStatus
import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext
import kotlinx.serialization.json.Json

/**
 * The handler for the authentication API.
 */
class AuthenticationHandler(
    private val provider: Provider,
) : Handler<RoutingContext> {

    override fun handle(routingContext: RoutingContext) {
        routingContext.request().body().onComplete {
            val login = Json.decodeFromString<Login>(it.result().toString())
            provider.webClient.authenticationRequest(login).onComplete {
                routingContext.response().setStatusCode(HttpResponseStatus.OK.code()).end()
            }
        }
    }
}
