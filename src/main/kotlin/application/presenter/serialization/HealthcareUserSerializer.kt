/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.serialization

import application.presenter.api.report.healthcareuser.HealthcareUserApiDto
import application.presenter.api.report.healthcareuser.PatientVitalSignsApiDto
import application.presenter.api.report.measurements.ValueWithUnit
import entity.surgeryreport.healthcareuser.HealthcareUser
import entity.surgeryreport.healthcareuser.PatientVitalSigns

/**
 * Serializers for data to return to API.
 */
object HealthcareUserSerializer {
    /**
     * Extension method to obtain the api dto of a healthcare user.
     */
    fun HealthcareUser.toApiDto(): HealthcareUserApiDto = HealthcareUserApiDto(
        taxCode = this.taxCode.value,
        name = this.name,
        surname = this.surname,
    )

    /**
     * Extension method to obtain the api dto of patient vital signs.
     */
    fun PatientVitalSigns.toApiDto(): PatientVitalSignsApiDto = PatientVitalSignsApiDto(
        heartBeat = this.heartBeat?.bpm,
        diastolicBloodPressure = this.diastolicBloodPressure?.pressure,
        systolicBloodPressure = this.systolicBloodPressure?.pressure,
        respiratoryRate = this.respiratoryRate?.rate,
        saturationPercentage = this.saturationPercentage?.percentage?.value,
        bodyTemperature = this.bodyTemperature?.temperature?.let { ValueWithUnit(it.value, it.unit.toString()) },
    )
}
