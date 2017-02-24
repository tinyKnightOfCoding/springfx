package ch.tkoc.fx.component.property

import ch.tkoc.fx.component.View
import javafx.scene.control.Button
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.scene.layout.FlowPane

class PropertyView : View<FlowPane>() {

    val email: TextField by fxid()
    val password: PasswordField by fxid()
    val login: Button by fxid()
}