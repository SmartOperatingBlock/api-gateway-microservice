/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.api.user

import entity.user.Password
import entity.user.User
import entity.user.UserId
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
        UserId(this.userId),
        Password(this.password),
    )

/**
 * Extension function to convert a User into a UserDto.
 */
fun User.toUserDto(): UserDto =
    UserDto(
        this.userId.id,
        this.password.password,
    )
