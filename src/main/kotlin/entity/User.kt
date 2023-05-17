/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package entity

import kotlinx.serialization.Serializable

/**
 * The user for the auth api composed by the [userId] and [password].
 */
@Serializable
data class User(
    val userId: String,
    val password: String,
)
