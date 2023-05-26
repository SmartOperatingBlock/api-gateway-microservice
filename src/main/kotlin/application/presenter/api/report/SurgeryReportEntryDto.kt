/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.api.report

import entity.surgeryreport.PatientId
import entity.surgeryreport.PatientName
import entity.surgeryreport.PatientSurname
import entity.surgeryreport.ProcessId
import entity.surgeryreport.SurgeryDate
import entity.surgeryreport.SurgeryReportEntry
import entity.surgeryreport.SurgicalProcessDescription
import kotlinx.serialization.Serializable
import java.time.Instant

/**
 * Class that model the principal information of a surgery report in the surgery report archive.
 * @param surgicalProcessID the id of the surgical process.
 * @param patientId the id of the patient.
 * @param patientName the name of the patient.
 * @param patientSurname the surname of the patient.
 * @param surgicalProcessDescription the description of the surgical process.
 * @param surgeryDate the date of the surgical process.
 */
@Serializable
data class SurgeryReportEntryDto(
    val surgicalProcessID: String,
    val patientId: String,
    val patientName: String?,
    val patientSurname: String?,
    val surgicalProcessDescription: String,
    val surgeryDate: String,
)

/**
 * Extension function to convert a SurgeryReportEntryDto into a SurgeryReportEntry.
 */
fun SurgeryReportEntryDto.toSurgeryReportEntry(): SurgeryReportEntry =
    SurgeryReportEntry(
        ProcessId(this.surgicalProcessID),
        PatientId(this.patientId),
        this.patientName?.let { PatientName(it) },
        this.patientSurname?.let { PatientSurname(it) },
        SurgicalProcessDescription(this.surgicalProcessDescription),
        SurgeryDate(Instant.parse(this.surgeryDate)),
    )

/**
 * Extension function to convert a SurgeryReportEntry into a SurgeryReportEntryDto.
 */
fun SurgeryReportEntry.toSurgeryReportEntryDto(): SurgeryReportEntryDto =
    SurgeryReportEntryDto(
        this.surgicalProcessID.id,
        this.patientId.id,
        this.patientName?.patientName,
        this.patientSurname?.surname,
        this.surgicalProcessDescription.description,
        this.surgeryDate.date.toString(),
    )
