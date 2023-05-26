/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package usecase.repository

import entity.surgeryreport.SurgeryReportEntry
import entity.surgeryreport.SurgeryReportIntegration
import entity.surgeryreport.report.SurgeryReport
import io.vertx.core.Future

/**
 * Repository to manage data about Surgery Reports.
 */
interface SurgeryReportRepository {

    /**
     * Get the surgery report archive.
     */
    fun getSurgeryReportArchive(): Future<List<SurgeryReportEntry>>

    /**
     * get the surgery report info given the [processId].
     */
    fun getSurgeryReportInfo(processId: String): Future<SurgeryReport>

    /**
     * Integrate the surgery report.
     * @param surgicalProcessId the id of the surgical process.
     * @param surgeryReportIntegration the integration of the surgery report.
     */
    fun integrateReport(surgicalProcessId: String, surgeryReportIntegration: SurgeryReportIntegration): Future<Boolean>
}
