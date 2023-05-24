/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.api.tracking

import kotlinx.serialization.Serializable

/**
 * Dto for tracking data in a room.
 * @param entries a list of tracking information.
 * @param total total number of entries.
 */
@Serializable
data class RoomsTrackingDataDto(
    val entries: List<Entries>,
    val total: Int,
)

/**
 * Tracking information.
 * @param dateTime the date time of the tracking.
 * @param roomId the room id.
 * @param healthProfessionalId the id of the health professional.
 * @param trackingType the type of the tracking.
 */
@Serializable
data class Entries(
    val dateTime: String,
    val roomId: String,
    val healthProfessionalId: String,
    val trackingType: String,
)
