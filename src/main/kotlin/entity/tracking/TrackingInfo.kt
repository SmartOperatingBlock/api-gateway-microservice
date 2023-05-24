/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package entity.tracking

/**
 * Models the tracking info about a healthprofessional.
 * @param healthProfessionalId the id of the health professional.
 * @param name the name of the health professional.
 * @param surname the surname of the health professional.
 * @param role the role of the health professional.
 * @param roomId the room id of the health professional.
 */
data class HealthProfessionalTrackingInfo(
    val healthProfessionalId: HealthProfessionalId,
    val name: HealthProfessionalName,
    val surname: HealthProfessionalSurname,
    val role: HealthProfessionalRole,
    val roomId: RoomId,
)

/**
 * The [id] of the health professional.
 */
data class HealthProfessionalId(val id: String)

/**
 * The [name] of the health professional.
 */
data class HealthProfessionalName(val name: String)

/**
 * The [surname] of the health professional.
 */
data class HealthProfessionalSurname(val surname: String)

/**
 * The [role] of the health professional.
 */
data class HealthProfessionalRole(val role: String)

/**
 * The [id] of the room.
 */
data class RoomId(val id: String)
