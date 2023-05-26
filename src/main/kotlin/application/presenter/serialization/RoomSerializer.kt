/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.serialization

import application.presenter.api.report.measurements.ValueWithUnit
import application.presenter.api.report.room.RoomApiDto
import application.presenter.api.report.room.RoomApiDtoType
import application.presenter.api.report.room.RoomEnvironmentalDataApiDto
import entity.surgeryreport.measurements.Humidity
import entity.surgeryreport.measurements.Luminosity
import entity.surgeryreport.measurements.Percentage
import entity.surgeryreport.measurements.Presence
import entity.surgeryreport.measurements.Temperature
import entity.surgeryreport.room.Room
import entity.surgeryreport.room.RoomEnvironmentalData
import entity.surgeryreport.room.RoomID
import entity.surgeryreport.room.RoomType

/**
 * Serializers for data to return to API.
 */
object RoomSerializer {
    /**
     * Extension method to obtain the api dto of a room.
     */
    fun Room.toApiDto(): RoomApiDto = RoomApiDto(
        id = this.id.value,
        type = this.type.toApiDtoType(),
    )

    /**
     * Extension method to obtain the room from the dto.
     */
    fun RoomApiDto.toRoom(): Room = Room(
        RoomID(this.id),
        this.type.toRoomType(),
    )

    /**
     * Extension method to obtain the api dto type of a room.
     */
    fun RoomType.toApiDtoType() = when (this) {
        RoomType.OPERATING_ROOM -> RoomApiDtoType.OPERATING_ROOM
        RoomType.PRE_OPERATING_ROOM -> RoomApiDtoType.PRE_OPERATING_ROOM
    }

    /**
     * Extension method to obtain the room type from the api dto.
     */
    fun RoomApiDtoType.toRoomType() = when (this) {
        RoomApiDtoType.OPERATING_ROOM -> RoomType.OPERATING_ROOM
        RoomApiDtoType.PRE_OPERATING_ROOM -> RoomType.PRE_OPERATING_ROOM
    }

    /**
     * Extension method to obtain the api dto of room environmental data.
     */
    fun RoomEnvironmentalData.toApiDto() = RoomEnvironmentalDataApiDto(
        temperature = this.temperature?.let { ValueWithUnit(it.value, it.unit.toString()) },
        humidity = this.humidity?.percentage?.value,
        luminosity = this.luminosity?.let { ValueWithUnit(it.value, it.unit.toString()) },
        presence = this.presence?.presenceDetected,
    )

    /**
     * Extension method to obtain room environmental data from dto.
     */
    fun RoomEnvironmentalDataApiDto.toRoomEnvironmentalData() = RoomEnvironmentalData(
        temperature = this.temperature?.let { Temperature(it.value) },
        humidity = this.humidity?.let { Percentage(it) }?.let { Humidity(it) },
        luminosity = this.luminosity?.let { Luminosity(it.value) },
        presence = this.presence?.let { Presence(it) },
    )
}
