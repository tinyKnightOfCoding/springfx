package ch.tkoc.fx.component.property

import ch.tkoc.fx.component.Component
import javafx.scene.control.PasswordField
import javafx.scene.layout.FlowPane

class PropertyViewWrongType : Component<FlowPane>() {

    val email: PasswordField by fxid()
}

