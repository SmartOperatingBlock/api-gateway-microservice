/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.api.report

import entity.surgeryreport.ReportIntegration
import entity.surgeryreport.SurgeryReportIntegration
import kotlinx.serialization.Serializable

/**
 * The Dto for surgery report integration.
 * @param reportIntegration the integration of the report.
 */
@Serializable
data class SurgeryReportIntegrationDto(
    val reportIntegration: String,
)

/**
 * Extension function used to convert [SurgeryReportIntegrationDto] to [SurgeryReportIntegration].
 */
fun SurgeryReportIntegrationDto.toSurgeryReportIntegration(): SurgeryReportIntegration =
    SurgeryReportIntegration(
        ReportIntegration(this.reportIntegration),
    )

/**
 * Extension function used to convert [SurgeryReportIntegration] to [SurgeryReportIntegrationDto].
 */
fun SurgeryReportIntegration.toSurgeryReportIngrationDto(): SurgeryReportIntegrationDto =
    SurgeryReportIntegrationDto(
        this.reportIntegration.text,
    )
