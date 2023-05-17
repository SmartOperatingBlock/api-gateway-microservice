/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package infrastructure.provider

import infrastructure.webclient.WebClient

/** The provider of web client. */
interface Provider {

    /** The web client to make http requests. */
    val webClient: WebClient
}
