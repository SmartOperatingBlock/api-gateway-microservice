/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package entity.user

/**
 * Models the information of a health professional.
 * @param name the name of the health professional.
 * @param surname the surname of the health professional.
 * @param role the role of the health professional.
 */
data class HealthProfessional(
    val name: Name,
    val surname: Surname,
    val role: Role,
)

/**
 * The [name] of the health professional.
 */
data class Name(val name: String) {
    init {
        require(name.isNotEmpty())
    }
}

/**
 * The [surname] of the health professional.
 */
data class Surname(val surname: String) {
    init {
        require(surname.isNotEmpty())
    }
}

/**
 * The [role] of the health professional.
 */
data class Role(val role: String) {
    init {
        require(role.isNotEmpty())
    }
}
