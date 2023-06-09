/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.serialization

import application.presenter.api.report.tracking.TrackApiDtoType
import application.presenter.api.report.tracking.TrackingInformationApiDto
import entity.surgeryreport.healthprofessional.HealthProfessionalID
import entity.surgeryreport.room.RoomID
import entity.surgeryreport.tracking.TrackType
import entity.surgeryreport.tracking.TrackingInfo
import java.time.Instant

/**
 * Serializers for data to return to API.
 */
object TrackingInformationSerializer {
    /**
     * Extension method to obtain the api dto of tracking information about an health professional.
     */
    fun TrackingInfo<HealthProfessionalID>.toApiDto(): TrackingInformationApiDto = TrackingInformationApiDto(
        dateTime = this.dateTime.toString(),
        healthProfessionalId = this.individual.value,
        roomID = this.roomID.value,
        trackType = this.trackType.toApiDtoType(),
    )

    private fun TrackType.toApiDtoType(): TrackApiDtoType = when (this) {
        TrackType.ENTER -> TrackApiDtoType.ENTER
        TrackType.EXIT -> TrackApiDtoType.EXIT
    }

    /**
     * Extension method to obtain tracking information about an health professional from dto.
     */
    fun TrackingInformationApiDto.toTrackingInfo(): TrackingInfo<HealthProfessionalID> = TrackingInfo(
        dateTime = Instant.parse(this.dateTime),
        individual = HealthProfessionalID(this.healthProfessionalId),
        roomID = RoomID(this.roomID),
        trackType = this.trackType.toTrackType(),
    )

    private fun TrackApiDtoType.toTrackType(): TrackType = when (this) {
        TrackApiDtoType.ENTER -> TrackType.ENTER
        TrackApiDtoType.EXIT -> TrackType.EXIT
    }
}
