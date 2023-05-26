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
import entity.surgeryreport.healthcareuser.TaxCode
import entity.surgeryreport.healthcareuser.VitalSign
import entity.surgeryreport.measurements.Percentage
import entity.surgeryreport.measurements.Temperature

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
     * Extension method to obtain the api dto of a healthcare user.
     */
    fun HealthcareUserApiDto.toHealthcareUser(): HealthcareUser = HealthcareUser(
        taxCode = TaxCode(this.taxCode),
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

    /**
     * Extension method to obtain the vital signs from dto.
     */
    fun PatientVitalSignsApiDto.toPatientVitalSigns(): PatientVitalSigns = PatientVitalSigns(
        heartBeat = this.heartBeat?.let { VitalSign.HeartBeat(it) },
        diastolicBloodPressure = this.diastolicBloodPressure?.let { VitalSign.DiastolicBloodPressure(it) },
        systolicBloodPressure = this.systolicBloodPressure?.let { VitalSign.SystolicBloodPressure(it) },
        respiratoryRate = this.respiratoryRate?.let { VitalSign.RespiratoryRate(it) },
        saturationPercentage = this.saturationPercentage?.let { Percentage(it) }
            ?.let { VitalSign.SaturationPercentage(it) },
        bodyTemperature = this.bodyTemperature?.let { Temperature(it.value) }?.let { VitalSign.BodyTemperature(it) },
    )
}
