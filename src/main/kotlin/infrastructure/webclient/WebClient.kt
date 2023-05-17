/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package infrastructure.webclient
import application.presenter.api.Login
import io.vertx.core.Future
import io.vertx.core.Vertx
import io.vertx.core.buffer.Buffer
import io.vertx.core.json.Json
import io.vertx.ext.web.client.HttpResponse
import io.vertx.ext.web.client.WebClient

/** The Web Client responsible to make HTTP request to other microservices. */
class WebClient(vertx: Vertx) {

    init {
        MICROSERVICES_URL.forEach {
            checkNotNull(it) {
                "Invalid Microservice URL!"
            }
        }
    }

    private val client: WebClient = WebClient.create(vertx)

    /** The authentication request. */
    fun authenticationRequest(login: Login): Future<HttpResponse<Buffer>> =
        client.postAbs(UMI_URI).sendJson(Json.encode(login))

    companion object {
        private val MICROSERVICES_URL = listOf<String?>(
            System.getenv("USER_MANAGEMENT_MICROSERVICE_URL"),
        )
        private val UMI_URI = System.getenv("USER_MANAGEMENT_MICROSERVICE_URL")
    }
}
