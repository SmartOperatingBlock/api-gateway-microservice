/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.serialization

import application.presenter.api.report.SurgeryReportApiDto
import application.presenter.api.report.SurgicalProcessStepAggregateDataApiDto
import application.presenter.api.report.measurements.AggregateDataApiDto
import application.presenter.api.report.medicaldevice.MedicalTechnologyUsageApiDto
import application.presenter.serialization.HealthcareUserSerializer.toApiDto
import application.presenter.serialization.HealthcareUserSerializer.toHealthcareUser
import application.presenter.serialization.HealthcareUserSerializer.toPatientVitalSigns
import application.presenter.serialization.MedicalDeviceSerializer.toApiDto
import application.presenter.serialization.MedicalDeviceSerializer.toImplantableMedicalDevice
import application.presenter.serialization.MedicalDeviceSerializer.toMedicalTechnology
import application.presenter.serialization.RoomSerializer.toApiDto
import application.presenter.serialization.RoomSerializer.toApiDtoType
import application.presenter.serialization.RoomSerializer.toRoom
import application.presenter.serialization.RoomSerializer.toRoomEnvironmentalData
import application.presenter.serialization.RoomSerializer.toRoomType
import application.presenter.serialization.SurgicalProcessSerializer.toApiDto
import application.presenter.serialization.SurgicalProcessSerializer.toSurgicalProcessStep
import application.presenter.serialization.TrackingInformationSerializer.toApiDto
import application.presenter.serialization.TrackingInformationSerializer.toTrackingInfo
import entity.surgeryreport.healthcareuser.PatientID
import entity.surgeryreport.healthprofessional.HealthProfessionalID
import entity.surgeryreport.measurements.AggregateData
import entity.surgeryreport.medicaldevice.MedicalTechnologyUsage
import entity.surgeryreport.process.SurgicalProcessID
import entity.surgeryreport.report.SurgeryProcessStepAggregateData
import entity.surgeryreport.report.SurgeryReport
import java.time.Instant

/**
 * Serializers for data to return to API.
 */
object SurgeryReportSerializer {
    /**
     * Extension method to obtain the surgery report api dto.
     * @return the surgery report dto for api.
     */
    fun SurgeryReport.toApiDto(): SurgeryReportApiDto = SurgeryReportApiDto(
        surgicalProcessID = this.surgicalProcessID.value,
        surgeryDate = this.surgeryDate.toString(),
        surgicalProcessDescription = this.surgicalProcessDescription,
        patientID = this.patientID.value,
        roomsInvolved = this.roomsInvolved.map { it.toApiDto() },
        inChargeHealthProfessionalID = this.inChargeHealthProfessional?.value,
        healthcareUser = this.healthcareUser?.toApiDto(),
        stepData = this.stepData
            .mapKeys { (step, _) -> step.toApiDto() }
            .mapValues { (_, data) -> data.toApiDto() },
        consumedImplantableMedicalDevice = this.consumedImplantableMedicalDevices.map { it.toApiDto() }.toSet(),
        medicalTechnologyUsageData = this.medicalTechnologyUsageData
            .map { MedicalTechnologyUsageApiDto(it.first.toString(), it.second.toApiDto()) }
            .toSet(),
        healthProfessionalTrackingInformation = this.healthProfessionalTrackingInformation.map { it.toApiDto() },
        additionalData = this.additionalData,
    )

    /**
     * Extension method to obtain the dto of a surgery report.
     * @return the surgery report dto for api.
     */
    fun SurgeryReportApiDto.toSurgeryReport(): SurgeryReport = SurgeryReport(
        surgicalProcessID = SurgicalProcessID(this.surgicalProcessID),
        surgeryDate = Instant.parse(this.surgeryDate),
        surgicalProcessDescription = this.surgicalProcessDescription,
        patientID = PatientID(this.patientID),
        roomsInvolved = this.roomsInvolved.map { it.toRoom() }.toSet(),
        inChargeHealthProfessional = this.inChargeHealthProfessionalID?.let { HealthProfessionalID(it) },
        healthcareUser = this.healthcareUser?.toHealthcareUser(),
        stepData = stepData
            .mapKeys { (step, _) -> step.toSurgicalProcessStep() }
            .mapValues { (_, data) -> data.toSurgicalProcessStepAggregateData() },
        consumedImplantableMedicalDevices = this.consumedImplantableMedicalDevice
            .map {
                it.toImplantableMedicalDevice()
            }.toSet(),
        medicalTechnologyUsageData = this.medicalTechnologyUsageData
            .map {
                MedicalTechnologyUsage(Instant.parse(it.dateTime), it.medicalTechnology.toMedicalTechnology())
            }.toSet(),
        healthProfessionalTrackingInformation = this.healthProfessionalTrackingInformation.map { it.toTrackingInfo() },
        additionalData = this.additionalData,
    )

    private fun SurgeryProcessStepAggregateData.toApiDto(): SurgicalProcessStepAggregateDataApiDto =
        SurgicalProcessStepAggregateDataApiDto(
            startDateTime = this.startDateTime.toString(),
            stopDateTime = this.stopDateTime?.toString(),
            patientVitalSignAggregateData = AggregateDataApiDto(
                average = this.patientVitalSignsAggregateData.average.toApiDto(),
                std = this.patientVitalSignsAggregateData.std.toApiDto(),
                maximum = this.patientVitalSignsAggregateData.maximum.toApiDto(),
                minimum = this.patientVitalSignsAggregateData.minimum.toApiDto(),
            ),
            environmentalAggregateData = this.environmentalAggregateData
                .mapKeys { (type, _) -> type.toApiDtoType() }
                .mapValues { (_, data) ->
                    AggregateDataApiDto(
                        average = data.average.toApiDto(),
                        std = data.std.toApiDto(),
                        maximum = data.maximum.toApiDto(),
                        minimum = data.minimum.toApiDto(),
                    )
                },
        )

    private fun SurgicalProcessStepAggregateDataApiDto.toSurgicalProcessStepAggregateData() =
        SurgeryProcessStepAggregateData(
            startDateTime = Instant.parse(this.startDateTime),
            stopDateTime = Instant.parse(this.stopDateTime),
            patientVitalSignsAggregateData = AggregateData(
                average = this.patientVitalSignAggregateData.average.toPatientVitalSigns(),
                std = this.patientVitalSignAggregateData.std.toPatientVitalSigns(),
                maximum = this.patientVitalSignAggregateData.maximum.toPatientVitalSigns(),
                minimum = this.patientVitalSignAggregateData.minimum.toPatientVitalSigns(),
            ),
            environmentalAggregateData = this.environmentalAggregateData
                .mapKeys { (type, _) -> type.toRoomType() }
                .mapValues { (_, data) ->
                    AggregateData(
                        average = data.average.toRoomEnvironmentalData(),
                        std = data.std.toRoomEnvironmentalData(),
                        maximum = data.maximum.toRoomEnvironmentalData(),
                        minimum = data.minimum.toRoomEnvironmentalData(),
                    )
                },
        )
}
