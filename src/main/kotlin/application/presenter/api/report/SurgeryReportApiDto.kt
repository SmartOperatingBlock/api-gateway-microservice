/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.api.report

import application.presenter.api.report.healthcareuser.HealthcareUserApiDto
import application.presenter.api.report.healthcareuser.PatientVitalSignsApiDto
import application.presenter.api.report.measurements.AggregateDataApiDto
import application.presenter.api.report.medicaldevice.ImplantableMedicalDeviceApiDto
import application.presenter.api.report.medicaldevice.MedicalTechnologyUsageApiDto
import application.presenter.api.report.process.SurgicalProcessStepApiDto
import application.presenter.api.report.room.RoomApiDto
import application.presenter.api.report.room.RoomApiDtoType
import application.presenter.api.report.room.RoomEnvironmentalDataApiDto
import application.presenter.api.report.tracking.TrackingInformationApiDto
import kotlinx.serialization.Serializable

/**
 * It represents the presentation of a single [entity.surgeryreport.report.SurgeryReport] entry.
 * The necessary information are the [surgicalProcessID], the [patientId], the [patientName], the [patientSurname],
 * the [surgicalProcessDescription] and the [surgeryDate].
 */
@Serializable
data class SurgeryReportEntry(
    val surgicalProcessID: String,
    val patientId: String,
    val patientName: String?,
    val patientSurname: String?,
    val surgicalProcessDescription: String,
    val surgeryDate: String,
)

/**
 * It represents the presentation of a [entity.surgeryreport.report.SurgeryReport].
 * The necessary information are: the [surgicalProcessID], the [surgeryDate], the [surgicalProcessDescription],
 * the [inChargeHealthProfessionalID], the [patientID], the [roomsInvolved], the [healthcareUser], the [stepData],
 * the [consumedImplantableMedicalDevice], the [medicalTechnologyUsageData], the [healthProfessionalTrackingInformation]
 * and the [additionalData].
 */
@Serializable
data class SurgeryReportApiDto(
    val surgicalProcessID: String,
    val surgeryDate: String,
    val surgicalProcessDescription: String,
    val patientID: String,
    val roomsInvolved: List<RoomApiDto>,
    val inChargeHealthProfessionalID: String?,
    val healthcareUser: HealthcareUserApiDto?,
    val stepData: Map<SurgicalProcessStepApiDto, SurgicalProcessStepAggregateDataApiDto>,
    val consumedImplantableMedicalDevice: Set<ImplantableMedicalDeviceApiDto>,
    val medicalTechnologyUsageData: Set<MedicalTechnologyUsageApiDto>,
    val healthProfessionalTrackingInformation: List<TrackingInformationApiDto>,
    val additionalData: String,
)

/**
 * Presenter class for the [entity.surgeryreport.report.SurgeryProcessStepAggregateData].
 * It represents its [startDateTime], [stopDateTime], [patientVitalSignAggregateData] and [environmentalAggregateData].
 */
@Serializable
data class SurgicalProcessStepAggregateDataApiDto(
    val startDateTime: String,
    val stopDateTime: String?,
    val patientVitalSignAggregateData: AggregateDataApiDto<PatientVitalSignsApiDto>,
    val environmentalAggregateData: Map<RoomApiDtoType, AggregateDataApiDto<RoomEnvironmentalDataApiDto>>,
)
