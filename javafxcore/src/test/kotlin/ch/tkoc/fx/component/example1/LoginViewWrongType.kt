package ch.tkoc.fx.component.example1

import ch.tkoc.fx.component.UiComponent
import javafx.scene.control.PasswordField
import javafx.scene.layout.FlowPane

class LoginViewWrongType : UiComponent<FlowPane>() {

    val email: PasswordField by fxid()
}

