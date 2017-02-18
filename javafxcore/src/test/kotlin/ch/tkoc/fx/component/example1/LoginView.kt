package ch.tkoc.fx.component.example1

import ch.tkoc.fx.component.UIComponent
import javafx.scene.control.Button
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.scene.layout.FlowPane

class LoginView: UIComponent<FlowPane>() {

    val email: TextField by fxid()
    val password: PasswordField by fxid()
    val login: Button by fxid()
}
