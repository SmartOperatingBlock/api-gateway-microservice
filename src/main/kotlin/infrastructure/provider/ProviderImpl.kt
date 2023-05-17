/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package infrastructure.provider

import infrastructure.webclient.WebClient
import io.vertx.core.Vertx

/** The implementation of the [Provider] interface. */
class ProviderImpl(vertx: Vertx) : Provider {

    override val webClient: WebClient = WebClient(vertx)
}
