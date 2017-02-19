package ch.tkoc.fx.component.binding

import ch.tkoc.fx.component.Component
import javafx.scene.layout.FlowPane


class BindingView : Component<FlowPane>() {

    var email: String by textfield()

}