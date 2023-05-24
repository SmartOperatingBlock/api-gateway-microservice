/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package entity.surgeryreport

/**
 * Models the surgery report integration.
 * @param reportIntegration the integration of the report.
 */
data class SurgeryReportIntegration(
    val reportIntegration: ReportIntegration,
)

/**
 * The integration of the report.
 * @param text the text of the integration.
 */
data class ReportIntegration(
    val text: String,
)
