package ch.tkoc.fx.component.property

import ch.tkoc.fx.component.Component
import javafx.scene.control.Button
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.scene.layout.FlowPane

class PropertyView : Component<FlowPane>() {

    val email: TextField by fxid()
    val password: PasswordField by fxid()
    val login: Button by fxid()
}
