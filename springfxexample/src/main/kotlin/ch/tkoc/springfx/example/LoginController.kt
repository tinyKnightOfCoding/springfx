package ch.tkoc.springfx.example

import ch.tkoc.springfx.context.annotation.FxTransition
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Component
class LoginController @Autowired constructor(val loginService: LoginService) {

    @FxTransition
    fun tryToLogin(username: String, password: String) : String {
        loginService.credentials = Credentials(username, password)
        if(loginService.loggedIn) {
            return "dataView"
        }
        return ":keep"
    }
}