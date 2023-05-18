/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.service

import entity.SurgeryReportEntry
import io.vertx.core.Future
import usecase.repository.SurgeryReportArchiveRepository

/**
 * Class that models surgery report archive application service.
 */
class SurgeryReportArchiveService(
    private val repository: SurgeryReportArchiveRepository,
) : ApplicationService<Future<List<SurgeryReportEntry>>> {

    override fun execute(): Future<List<SurgeryReportEntry>> = repository.getSurgeryReportArchive()
}
