/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package usecase.repository

import entity.process.SurgicalProcess
import entity.room.RoomData
import io.vertx.core.Future

/**
 * Repository to manage and obtain data for Surgical Processes.
 */
interface SurgicalProcessRepository {

    /**
     * Get a [SurgicalProcess] by the [preOperatingRoomId] and the [operatingRoomId].
     */
    fun getSurgicalProcessInfoByRoomId(
        preOperatingRoomId: RoomData.RoomId,
        operatingRoomId: RoomData.RoomId,
    ): Future<SurgicalProcess?>
}
