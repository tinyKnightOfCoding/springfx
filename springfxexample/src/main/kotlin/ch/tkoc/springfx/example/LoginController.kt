package ch.tkoc.springfx.example

import ch.tkoc.springfx.context.annotation.Transition
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Component
class LoginController @Autowired constructor(val loginService: LoginService) {

    @Transition
    fun tryLogin(username: String, password: String) : String {
        loginService.credentials = Credentials(username, password)
        if(loginService.loggedIn) {
            return ":exit"
        }
        return ":keep"
    }
}