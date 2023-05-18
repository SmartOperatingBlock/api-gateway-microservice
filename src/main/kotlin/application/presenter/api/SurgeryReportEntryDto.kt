/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.api

import entity.PatientInfo
import entity.ProcessDate
import entity.ProcessId
import entity.ProcessType
import entity.SurgeryReportEntry
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.Instant

/**
 * Class that model the principal information of a surgery report in the surgery report archive.
 * @param processId the id of the surgery process.
 * @param patientInfo the name and the surname of the patient.
 * @param processType the type of the surgery.
 * @param processDate the date of the surgery.
 */
@Serializable
data class SurgeryReportEntryDto(
    val processId: String,
    val patientInfo: String,
    val processType: String,
    @Contextual val processDate: Instant,
)

/**
 * Extension function to convert a SurgeryReportEntryDto into a SurgeryReportEntry.
 */
fun SurgeryReportEntryDto.toSurgeryReportEntry(): SurgeryReportEntry =
    SurgeryReportEntry(
        ProcessId(this.processId),
        PatientInfo(this.patientInfo),
        ProcessType(this.processType),
        ProcessDate(this.processDate),
    )

/**
 * Extension function to convert a SurgeryReportEntry into a SurgeryReportEntryDto.
 */
fun SurgeryReportEntry.toSurgeryReportEntryDto(): SurgeryReportEntryDto =
    SurgeryReportEntryDto(
        this.processId.id,
        this.patientInfo.patientInfo,
        this.processType.processType,
        this.processDate.processDate,
    )
