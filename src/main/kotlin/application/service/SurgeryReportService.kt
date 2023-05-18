/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.service

import entity.report.SurgeryReportEntry
import entity.report.SurgeryReportInfo
import io.vertx.core.Future
import usecase.repository.SurgeryReportArchiveRepository
import usecase.repository.SurgeryReportInfoRepository

/**
 * Module with all ApplicationService for SurgeryReport.
 */
object SurgeryReportService {

    /**
     * Class that models surgery report archive application service.
     */
    class SurgeryReportArchiveService(
        private val repository: SurgeryReportArchiveRepository,
    ) : ApplicationService<Future<List<SurgeryReportEntry>>> {

        override fun execute(): Future<List<SurgeryReportEntry>> = repository.getSurgeryReportArchive()
    }

    /**
     * Class that models surgery report info application service.
     */
    class SurgeryReportInfoService(
        private val processId: String,
        private val repository: SurgeryReportInfoRepository,
    ) : ApplicationService<Future<SurgeryReportInfo>> {

        override fun execute(): Future<SurgeryReportInfo> = repository.getSurgeryReportInfo(processId)
    }
}
