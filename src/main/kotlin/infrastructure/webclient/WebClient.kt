/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package infrastructure.webclient
import application.presenter.api.RoomDto
import application.presenter.api.toRoom
import application.presenter.api.toUserDto
import application.presenter.api.util.ApiResponses
import entity.room.Room
import entity.user.User
import io.netty.handler.codec.http.HttpResponseStatus
import io.vertx.core.Future
import io.vertx.core.Vertx
import io.vertx.ext.web.client.WebClient
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import usecase.repository.AuthenticationRepository
import usecase.repository.RoomRepository

/** The Web Client responsible to make HTTP request to other microservices. */
class WebClient(vertx: Vertx) : AuthenticationRepository, RoomRepository {

    init {
        MICROSERVICES_URL.forEach {
            checkNotNull(it) {
                "Invalid Microservice URL!"
            }
        }
    }

    private val client: WebClient = WebClient.create(vertx)

    override fun getUserAuthentication(user: User): Future<Boolean> =
        client.postAbs("$UMI_URI/auth").sendJson(Json.encodeToString(user.toUserDto())).map {
            it.statusCode() == HttpResponseStatus.OK.code()
        }

    override fun getRooms(): Future<List<Room>> =
        client.getAbs("$BM_URI/rooms").send().map {
            Json.decodeFromString<ApiResponses.ResponseEntryList<ApiResponses.ResponseEntry<RoomDto>>>(
                it.bodyAsString(),
            ).entries.map { responseEntry ->
                responseEntry.entry.toRoom()
            }
        }

    companion object {
        private val UMI_URI = System.getenv("USER_MANAGEMENT_MICROSERVICE_URL")
        private val BM_URI = System.getenv("BUILDING_MANAGEMENT_MICROSERVICE_URL")
        private val MICROSERVICES_URL = listOf<String?>(
            UMI_URI,
            BM_URI,
        )
    }
}
