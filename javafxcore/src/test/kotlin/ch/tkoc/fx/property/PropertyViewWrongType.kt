package ch.tkoc.fx.property

import ch.tkoc.fx.View
import javafx.scene.control.PasswordField
import javafx.scene.layout.FlowPane

class PropertyViewWrongType : View<FlowPane>() {

    val email: PasswordField by fxid()
}

