/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.api.room

import application.presenter.api.room.RoomPresentation.RoomApiDtoType
import application.presenter.api.room.RoomPresentation.RoomDto
import application.presenter.api.room.RoomPresentation.RoomEntryDto
import entity.environment.EnvironmentData
import entity.environment.RoomEnvironmentalData
import entity.room.Room
import entity.room.RoomData
import entity.room.RoomData.RoomType

/**
 * Module with all methods to serialize and deserialize room data.
 */
object RoomSerialization {

    /**
     * Extension method to obtain the room entry information.
     * @return the room entry.
     */
    fun Room.toRoomEntry(): RoomEntryDto = RoomEntryDto(
        id = this.id.id,
        name = this.name.orEmpty(),
        zoneId = this.zoneId.id,
        type = this.type.toRoomApiDtoType(),
    )

    /**
     * Extension method to obtain the room information from a room entry.
     * @return the room.
     */
    fun RoomEntryDto.toRoom() = Room(
        RoomData.RoomId(this.id),
        this.name,
        RoomData.ZoneId(this.zoneId),
        this.type.toRoomType(),
    )

    /**
     * Extension method to convert [Room] API DTO to [RoomDto] class.
     */
    fun Room.toRoomDto(): RoomDto = RoomDto(
        id = this.id.id,
        name = this.name.orEmpty(),
        zoneId = this.zoneId.id,
        type = this.type.toRoomApiDtoType(),
        environmentalData = this.environmentalData.toEnvironmentDataApiDto(),
    )

    /**
     * Extension method to convert [RoomDto] API to [Room] class.
     */
    fun RoomDto.toRoom(): Room = Room(
        RoomData.RoomId(this.id),
        this.name,
        RoomData.ZoneId(this.zoneId),
        this.type.toRoomType(),
        this.environmentalData.toRoomEnvironmentalData(),
    )

    private fun RoomEnvironmentalData.toEnvironmentDataApiDto() = RoomPresentation.EnvironmentalDataApiDto(
        temperature = this.temperature?.let { RoomPresentation.ValueWithUnit(it.value, it.unit.toString()) },
        humidity = this.humidity?.percentage,
        luminosity = this.luminosity?.let { RoomPresentation.ValueWithUnit(it.value, it.unit.toString()) },
        presence = this.presence?.presenceDetected,
    )

    private fun RoomPresentation.EnvironmentalDataApiDto.toRoomEnvironmentalData() = RoomEnvironmentalData(
        temperature = this.temperature?.let { EnvironmentData.Temperature(it.value) },
        humidity = this.humidity?.let { EnvironmentData.Humidity(it) },
        luminosity = this.luminosity?.let { EnvironmentData.Luminosity(it.value) },
        presence = this.presence?.let { EnvironmentData.Presence(it) },
    )

    private fun RoomType.toRoomApiDtoType(): RoomApiDtoType = when (this) {
        RoomType.OPERATING_ROOM -> RoomApiDtoType.OPERATING_ROOM
        RoomType.PRE_OPERATING_ROOM -> RoomApiDtoType.PRE_OPERATING_ROOM
    }

    private fun RoomApiDtoType.toRoomType(): RoomType = when (this) {
        RoomApiDtoType.OPERATING_ROOM -> RoomType.OPERATING_ROOM
        RoomApiDtoType.PRE_OPERATING_ROOM -> RoomType.PRE_OPERATING_ROOM
    }
}
