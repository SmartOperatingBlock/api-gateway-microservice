/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package usecase

import entity.report.SurgeryReportIntegration
import io.vertx.core.Future
import usecase.repository.SurgeryReportIntegrationRepository

/**
 * Class that models surgery report integration use case.
 */
class SurgeryReportIntegrationUseCase(
    private val integration: SurgeryReportIntegration,
    private val repository: SurgeryReportIntegrationRepository,
) : UseCase<Future<Boolean>> {

    override fun execute(): Future<Boolean> = repository.integrateReport(integration)
}
