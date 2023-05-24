/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package usecase.repository

import entity.tracking.HealthProfessionalTrackingInfo
import io.vertx.core.Future

/**
 * Interface that model tracking repository.
 */
interface TrackingRepository {

    /**
     * Get the info about health professionals tracking given the [preOperatingRoomId] and the [operatingRoomId].
     */
    fun getZoneHealthProfessionalTrackingInfo(
        preOperatingRoomId: String,
        operatingRoomId: String,
    ): Pair<Future<List<Future<HealthProfessionalTrackingInfo>>>, Future<List<Future<HealthProfessionalTrackingInfo>>>>

    /**
     * Get the info about health professionals tracking in the operating block.
     */
    fun getBlockHealthProfessionalTrackingInfo(): Future<List<Future<HealthProfessionalTrackingInfo>>>
}
