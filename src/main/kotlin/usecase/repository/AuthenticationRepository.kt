/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package usecase.repository

import entity.User
import io.vertx.core.Future

/**
 * interface for user authentication repository.
 */
interface AuthenticationRepository {

    /**
     * Authenticate a user.
     * @return a [Future] of boolean. The boolean will be true if the user is authenticated, false otherwise.
     */
    fun getUserAuthentication(user: User): Future<Boolean>
}
