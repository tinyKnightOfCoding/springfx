package ch.tkoc.springfx.example

import ch.tkoc.springfx.context.annotation.FxComponent
import ch.tkoc.springfx.context.annotation.FxView
import javafx.event.EventHandler
import javafx.scene.control.Button
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.scene.layout.FlowPane
import org.springframework.beans.factory.annotation.Autowired
import java.awt.event.ActionEvent

@FxView(initial = true)
@FxComponent
class LoginView @Autowired constructor(val loginController: LoginController): FlowPane() {

    val username: TextField get() = lookup("#username")!! as TextField

    val password: PasswordField get() = lookup("#password")!! as PasswordField

    val submit: Button get() = lookup("#login")!! as Button


    fun onShow() {
        submit.onAction = EventHandler {
            loginController.tryLogin(username.text, password.text)
        }
    }
}