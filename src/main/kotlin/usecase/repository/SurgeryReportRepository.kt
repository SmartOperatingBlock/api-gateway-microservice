/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package usecase.repository

import entity.surgeryreport.SurgeryReportEntry
import entity.surgeryreport.SurgeryReportInfo
import entity.surgeryreport.SurgeryReportIntegration
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
    fun getSurgeryReportInfo(processId: String): Future<SurgeryReportInfo>

    /**
     * Integrate the surgery report.
     * @param surgeryReportIntegration the integration of the surgery report.
     */
    fun integrateReport(surgeryReportIntegration: SurgeryReportIntegration): Future<Boolean>
}
