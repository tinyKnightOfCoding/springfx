package ch.tkoc.fx.component.example1

import ch.tkoc.fx.component.UIComponent
import javafx.scene.control.TextField
import javafx.scene.layout.FlowPane

class LoginViewWrongId : UIComponent<FlowPane>() {

    val username: TextField by fxid()
}
