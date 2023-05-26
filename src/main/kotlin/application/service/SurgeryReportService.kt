/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.service

import entity.surgeryreport.SurgeryReportEntry
import entity.surgeryreport.report.SurgeryReport
import io.vertx.core.Future
import usecase.repository.SurgeryReportRepository

/**
 * Module with all ApplicationService for SurgeryReport.
 */
object SurgeryReportService {

    /**
     * Class that models surgery report archive application service.
     */
    class SurgeryReportArchiveService(
        private val repository: SurgeryReportRepository,
    ) : ApplicationService<Future<List<SurgeryReportEntry>>> {

        override fun execute(): Future<List<SurgeryReportEntry>> = repository.getSurgeryReportArchive()
    }

    /**
     * Class that models surgery report info application service.
     */
    class SurgeryReportInfoService(
        private val processId: String,
        private val repository: SurgeryReportRepository,
    ) : ApplicationService<Future<SurgeryReport>> {

        override fun execute(): Future<SurgeryReport> = repository.getSurgeryReportInfo(processId)
    }
}
