/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package entity.room

import entity.environment.RoomEnvironmentalData

/**
 * The model of the Room entity composed by [id], [name],
 * [zoneId], the [type] and the [environmentalData].
 */
data class Room(
    val id: RoomData.RoomId,
    val name: String?,
    val zoneId: RoomData.ZoneId,
    val type: RoomData.RoomType,
    val environmentalData: RoomEnvironmentalData = RoomEnvironmentalData(),
) {
    override fun equals(other: Any?): Boolean = when {
        other === this -> true
        other is Room -> this.id == other.id
        else -> false
    }

    override fun hashCode(): Int = this.id.hashCode()
}
