/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.service

import entity.room.RoomData
import entity.tracking.HealthProfessionalTrackingInfo
import io.vertx.core.Future
import usecase.repository.TrackingRepository

/**
 * Module with all the Application Services for Tracking.
 */
object TrackingService {

    /**
     * Get a [HealthProfessionalTrackingInfo] by the [preOperatingRoomId] and the [operatingRoomId]
     * using a [trackingRepository].
     */
    class ZoneHealthProfessionalTrackingInfo(
        private val preOperatingRoomId: RoomData.RoomId,
        private val operatingRoomId: RoomData.RoomId,
        private val trackingRepository: TrackingRepository,
    ) : ApplicationService<Pair<Future<List<Future<HealthProfessionalTrackingInfo>>>,
        Future<List<Future<HealthProfessionalTrackingInfo>>>,>,> {

        override fun execute(): Pair<Future<List<Future<HealthProfessionalTrackingInfo>>>,
            Future<List<Future<HealthProfessionalTrackingInfo>>>,> =
            trackingRepository.getZoneHealthProfessionalTrackingInfo(preOperatingRoomId.id, operatingRoomId.id)
    }

    /**
     * Get a [HealthProfessionalTrackingInfo] using a [trackingRepository].
     */
    class BlockHealthProfessionalTrackingInfo(
        private val trackingRepository: TrackingRepository,
    ) : ApplicationService<Future<List<Future<HealthProfessionalTrackingInfo>>>> {

        override fun execute(): Future<List<Future<HealthProfessionalTrackingInfo>>> =
            trackingRepository.getBlockHealthProfessionalTrackingInfo()
    }
}
