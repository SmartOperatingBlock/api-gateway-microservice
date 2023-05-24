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
import entity.surgeryreport.tracking.TrackType
import entity.surgeryreport.tracking.TrackingInfo

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
}
