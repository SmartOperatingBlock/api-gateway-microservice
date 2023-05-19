/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.service

import entity.process.SurgicalProcess
import entity.room.RoomData
import io.vertx.core.Future
import usecase.repository.SurgicalProcessRepository

/**
 * Module with all the Application Services for Surgical Processes.
 */
object ProcessService {

    /**
     * Get a [SurgicalProcess] by the [preOperatingRoomId] and the [operatingRoomId] using a [processRepository].
     */
    class GetProcessInfoByRoomId(
        private val preOperatingRoomId: RoomData.RoomId?,
        private val operatingRoomId: RoomData.RoomId?,
        private val processRepository: SurgicalProcessRepository,
    ) : ApplicationService<Future<SurgicalProcess?>> {

        override fun execute(): Future<SurgicalProcess?> =
            processRepository.getSurgicalProcessInfoByRoomId(preOperatingRoomId, operatingRoomId)
    }
}
