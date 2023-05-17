/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.api

import entity.user.User
import kotlinx.serialization.Serializable

/**
 * The user dto for the auth api composed by the [userId] and [password].
 */
@Serializable
data class UserDto(
    val userId: String,
    val password: String,
)

/**
 * Extension function to convert a UserDto into a User.
 */
fun UserDto.toUser(): User =
    User(
        this.userId,
        this.password,
    )

/**
 * Extension function to convert a User into a UserDto.
 */
fun User.toUserDto(): UserDto =
    UserDto(
        this.userId,
        this.password,
    )
