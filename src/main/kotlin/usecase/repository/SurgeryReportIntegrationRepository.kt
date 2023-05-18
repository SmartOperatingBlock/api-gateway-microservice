/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package usecase.repository

import entity.report.SurgeryReportIntegration
import io.vertx.core.Future

/**
 * Repository for the surgery report integration.
 */
interface SurgeryReportIntegrationRepository {

    /**
     * Integrate the surgery report.
     * @param surgeryReportIntegration the integration of the surgery report.
     */
    fun integrateReport(surgeryReportIntegration: SurgeryReportIntegration): Future<Boolean>
}
