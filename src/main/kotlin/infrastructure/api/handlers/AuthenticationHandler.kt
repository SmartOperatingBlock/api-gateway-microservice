/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package infrastructure.api.handlers

import application.presenter.api.UserDto
import application.presenter.api.toUser
import infrastructure.provider.Provider
import io.netty.handler.codec.http.HttpResponseStatus
import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext
import kotlinx.serialization.json.Json
import usecase.AuthenticationUseCase

/**
 * The handler for the authentication API.
 */
class AuthenticationHandler(
    private val provider: Provider,
) : Handler<RoutingContext> {

    override fun handle(routingContext: RoutingContext) {
        routingContext.request().body().onComplete {
            val user = Json.decodeFromString<UserDto>(it.result().toString()).toUser()
            AuthenticationUseCase(user, provider.webClient).execute().onComplete { ar ->
                routingContext
                    .response()
                    .setStatusCode(
                        if (ar.result()) HttpResponseStatus.OK.code() else HttpResponseStatus.UNAUTHORIZED.code(),
                    ).end()
            }
        }
    }
}
