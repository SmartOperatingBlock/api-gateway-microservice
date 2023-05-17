/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.api

import kotlinx.serialization.Serializable

/**
 * The login dto for the auth api composed by the [userId] and [password].
 */
@Serializable
data class Login(
    val userId: String,
    val password: String,
)
