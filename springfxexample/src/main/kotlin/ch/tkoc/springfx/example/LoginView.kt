package ch.tkoc.springfx.example


import ch.tkoc.springfx.context.annotation.View
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Button
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.scene.layout.FlowPane
import org.springframework.beans.factory.annotation.Autowired

@View(initial = true)
class LoginView @Autowired constructor(val loginController: LoginController): FlowPane() {

    val username: TextField get() = lookup("#username")!! as TextField

    val password: PasswordField get() = lookup("#password")!! as PasswordField

    val submit: Button get() = lookup("#login")!! as Button
    
    fun onShow() {
        submit.onAction = EventHandler<ActionEvent> {
            loginController.tryLogin(username.text, password.text)
        }
    }
}
