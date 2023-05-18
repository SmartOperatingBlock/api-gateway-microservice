/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package usecase.repository

import entity.SurgeryReportEntry
import io.vertx.core.Future

/**
 * Repository for surgery report archive.
 */
interface SurgeryReportArchiveRepository {

    /**
     * Get the surgery report archive.
     */
    fun getSurgeryReportArchive(): Future<List<SurgeryReportEntry>>
}
