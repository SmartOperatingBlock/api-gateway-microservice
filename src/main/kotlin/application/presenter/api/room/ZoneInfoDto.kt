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
 * The Dto for the information about a Zone of the Smart Operating Block.
 * It includes the [zoneId], the [preOperatingRoomId] and the [operatingRoomId].
 */
@Serializable
data class ZoneInfoDto(
    val zoneId: String,
    val preOperatingRoomId: String?,
    val operatingRoomId: String?,
)
