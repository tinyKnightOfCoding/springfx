package ch.tkoc.fx.component.example1

import ch.tkoc.fx.component.UiComponent
import javafx.scene.control.TextField
import javafx.scene.layout.FlowPane

class LoginViewWrongId : UiComponent<FlowPane>() {

    val username: TextField by fxid()
}
