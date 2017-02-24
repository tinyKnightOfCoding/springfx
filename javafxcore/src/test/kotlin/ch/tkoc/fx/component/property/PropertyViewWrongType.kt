package ch.tkoc.fx.component.property

import ch.tkoc.fx.component.View
import javafx.scene.control.PasswordField
import javafx.scene.layout.FlowPane

class PropertyViewWrongType : View<FlowPane>() {

    val email: PasswordField by fxid()
}

