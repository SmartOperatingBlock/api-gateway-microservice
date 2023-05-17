/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package usecase

import entity.user.User
import io.vertx.core.Future
import usecase.repository.AuthenticationRepository

/**
 * Class that models authentication use case.
 */
class AuthenticationUseCase(
    private val user: User,
    private val repository: AuthenticationRepository,
) : UseCase<Future<Boolean>> {

    override fun execute(): Future<Boolean> = repository.getUserAuthentication(user)
}
