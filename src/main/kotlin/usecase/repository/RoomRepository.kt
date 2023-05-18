/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package usecase.repository

import entity.room.Room
import entity.room.RoomData
import io.vertx.core.Future

/**
 * The repository for [Room] data.
 */
interface RoomRepository {

    /**
     * Return a list with all the [Room] that are within the Operating Block.
     */
    fun getRooms(): Future<List<Room>>

    /**
     * Return the environmental information of a [Room] by its [roomId].
     */
    fun getRoomEnvironmentalInfo(roomId: RoomData.RoomId): Future<Room>
}
