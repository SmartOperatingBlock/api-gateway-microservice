/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter

import application.presenter.api.user.toUser
import application.presenter.api.user.toUserDto
import entity.user.Password
import entity.user.User
import entity.user.UserId
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class TestUserDto : StringSpec({

    val user = User(UserId("uid"), Password("password"))

    "A user should be correctly converted to its dto" {
        user.toUserDto() shouldNotBe null
        user.toUserDto().userId shouldBeEqual user.userId.id
        user.toUserDto().password shouldBeEqual user.password.password
    }

    "A user dto should be correctly converted to its entity" {
        user.toUserDto().toUser() shouldBe user
    }
})
