import infrastructure.api.ObdApiGatewayVerticle
import infrastructure.api.OrdApiGatewayVerticle
import infrastructure.provider.Provider
import infrastructure.provider.ProviderImpl
import io.vertx.core.Vertx

/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

/**
 * The launcher of the API Gateway.
 */
fun main() {
    val vertx = Vertx.vertx()
    val provider: Provider = ProviderImpl(vertx)
    vertx.deployVerticle(ObdApiGatewayVerticle(provider))
    vertx.deployVerticle(OrdApiGatewayVerticle(provider))
}
