package ch.tkoc.fx.component.example1

import ch.tkoc.fx.component.UIComponent
import javafx.scene.control.PasswordField
import javafx.scene.layout.FlowPane

class LoginViewWrongType : UIComponent<FlowPane>() {

    val email: PasswordField by fxid()
}

