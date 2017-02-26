package ch.tkoc.fx.binding

import ch.tkoc.fx.View
import ch.tkoc.fx.button
import ch.tkoc.fx.textfield
import javafx.event.ActionEvent
import javafx.scene.layout.FlowPane


class BindingView : View<FlowPane>() {

    var email: String by textfield()

    var login: (ActionEvent) -> Unit by button()
}