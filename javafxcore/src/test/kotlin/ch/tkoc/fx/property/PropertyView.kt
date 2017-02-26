package ch.tkoc.fx.property

import ch.tkoc.fx.View
import ch.tkoc.fx.fxid
import javafx.scene.control.Button
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.scene.layout.FlowPane

class PropertyView : View<FlowPane>() {

    val email: TextField by fxid()
    val password: PasswordField by fxid()
    val login: Button by fxid()
}
