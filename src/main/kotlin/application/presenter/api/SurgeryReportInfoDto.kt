/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.api

import entity.PatientName
import entity.SurgeryId
import entity.SurgeryName
import entity.SurgeryReportEntry
import entity.SurgeryReportInfo
import kotlinx.serialization.Serializable

/**
 * The dto for surgery report info.
 * @param surgeryId the id of the surgery.
 * @param surgeryName the name of the surgery.
 * @param patientName the name of the patient.
 */
@Serializable
data class SurgeryReportInfoDto(
    val surgeryId: String,
    val surgeryName: String,
    val patientName: String,
)

/**
 * Extension function to convert a [SurgeryReportEntry] to [SurgeryReportInfoDto].
 */
fun SurgeryReportInfo.toSurgeryReportInfoDto(): SurgeryReportInfoDto =
    SurgeryReportInfoDto(
        this.surgeryId.id,
        this.surgeryName.surgeryName,
        this.patientName.patientName,
    )

/**
 * Extension function to convert a [SurgeryReportInfoDto] to [SurgeryReportEntry].
 */
fun SurgeryReportInfoDto.toSurgeryNameInfo(): SurgeryReportInfo =
    SurgeryReportInfo(
        SurgeryId(this.surgeryId),
        SurgeryName(this.surgeryName),
        PatientName(this.patientName),
    )