package ch.tkoc.springfx.example

import org.springframework.stereotype.Component

@Component
class LoginService {

    var credentials: Credentials? = null
        set(value) {
            field = credentials
            update()
        }

    var loggedIn: Boolean = false

    private fun update() = credentials!!.apply {
        loggedIn = username == "kenneth" && password == "banana"
    }
}