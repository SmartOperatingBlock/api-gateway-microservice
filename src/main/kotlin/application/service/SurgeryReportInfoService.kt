/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.service

import entity.SurgeryReportInfo
import io.vertx.core.Future
import usecase.repository.SurgeryReportInfoRepository

/**
 * Class that models surgery report info application service.
 */
class SurgeryReportInfoService(
    private val processId: String,
    private val repository: SurgeryReportInfoRepository,
) : ApplicationService<Future<SurgeryReportInfo>> {

    override fun execute(): Future<SurgeryReportInfo> = repository.getSurgeryReportInfo(processId)
}
