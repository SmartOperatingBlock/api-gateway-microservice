/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.api.room

import kotlinx.serialization.Serializable

/**
 * Models the [operatingRoomId] and the [preOperatingRoomId] in a zone.
 */
@Serializable
class RoomsId(
    val operatingRoomId: String,
    val preOperatingRoomId: String,
)
