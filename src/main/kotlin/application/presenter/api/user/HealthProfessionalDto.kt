/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.api.user

import entity.user.HealthProfessional
import entity.user.Name
import entity.user.Role
import entity.user.Surname
import kotlinx.serialization.Serializable

/**
 * Models the dto of a health professional.
 * @param healthProfessionalId the id of the health professional.
 * @param name the name of the health professional.
 * @param surname the gender of the health professional.
 * @param gender the id of the health professional.
 * @param birthdate the birthdate of the health professional.
 * @param emailAddress the email of the health professional.
 * @param phoneNumber the phone number of the health professional.
 * @param role the role of the health professional.
 */
@Serializable
class HealthProfessionalDto(
    val healthProfessionalId: String,
    val name: String,
    val surname: String,
    val gender: String,
    val birthdate: String,
    val emailAddress: String,
    val phoneNumber: String,
    val role: String,
)

/**
 * Extension function to convert an [HealthProfessionalDto] in [HealthProfessional].
 */
fun HealthProfessionalDto.toHealthProfessional(): HealthProfessional =
    HealthProfessional(
        Name(this.name),
        Surname(this.surname),
        Role(this.role),
    )
