/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package entity.user

/**
 * The user for the auth api composed by the [userId] and [password].
 */
data class User(
    val userId: UserId,
    val password: Password,
)

/**
 * The [id] of the user.
 */
data class UserId(val id: String) {
    init {
        require(id.isNotEmpty())
    }
}

/**
 * The [password] of the user.
 */
data class Password(val password: String) {
    init {
        require(password.isNotEmpty())
    }
}
