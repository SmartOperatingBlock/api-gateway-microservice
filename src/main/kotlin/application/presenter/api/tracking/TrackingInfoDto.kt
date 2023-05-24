/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.api.tracking

import entity.tracking.HealthProfessionalTrackingInfo
import kotlinx.serialization.Serializable

/**
 * Dto for tracking info of a health professional.
 * @param healthProfessionalId the id of the health professional.
 * @param healthProfessionalName the name of the health professional.
 * @param healthProfessionalSurname the surname of the health professional.
 * @param healthProfessionalRole the role of the health professional.
 * @param room the room where is the health professional.
 */
@Serializable
data class TrackingInfoDto(
    val healthProfessionalId: String,
    val healthProfessionalName: String,
    val healthProfessionalSurname: String,
    val healthProfessionalRole: String,
    val room: String,
)

/**
 * Extension function to convert [HealthProfessionalTrackingInfo] in a [TrackingInfoDto].
 */
fun HealthProfessionalTrackingInfo.toTrackingInfoDto(): TrackingInfoDto =
    TrackingInfoDto(
        this.healthProfessionalId.id,
        this.name.name,
        this.surname.surname,
        this.role.role,
        this.roomId.id,
    )
