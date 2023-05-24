/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package entity

import entity.user.Password
import entity.user.User
import entity.user.UserId
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldNotBe

class TestUser : StringSpec({

    "An user shouldn't have an empty user id" {
        shouldThrow<IllegalArgumentException> {
            User(userId = UserId(""), Password("password"))
        }
    }

    "An user shouldn't have an empty password" {
        shouldThrow<IllegalArgumentException> {
            User(userId = UserId("userId"), Password(""))
        }
    }

    "It should be possible to create a user" {
        User(userId = UserId("userId"), Password("password")) shouldNotBe null
    }
})
