/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package entity.environment

import kotlinx.serialization.Serializable

/**
 * Wraps all the environmental data associated to a Room.
 * So it describe:
 * - the [temperature] inside the room
 * - the [humidity] inside the room
 * - the [luminosity] inside the room
 * - the [presence] of someone in the room
 * All the data may be not present.
 */
@Serializable
data class RoomEnvironmentalData(
    val temperature: EnvironmentData.Temperature? = null,
    val humidity: EnvironmentData.Humidity? = null,
    val luminosity: EnvironmentData.Luminosity? = null,
    val presence: EnvironmentData.Presence? = null,
)
