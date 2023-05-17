/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.service

import entity.room.Room
import io.vertx.core.Future
import usecase.repository.RoomRepository

/**
 * Module with all [ApplicationService] of [Room].
 */
object RoomService {

    /**
     * The [ApplicationService] to get all rooms of the Operating Block.
     */
    class GetRooms(
        private val roomRepository: RoomRepository,
    ) : ApplicationService<Future<List<Room>>> {

        override fun execute(): Future<List<Room>> = roomRepository.getRooms()
    }
}
