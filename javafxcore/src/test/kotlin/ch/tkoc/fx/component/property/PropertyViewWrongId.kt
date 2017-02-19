package ch.tkoc.fx.component.property

import ch.tkoc.fx.component.Component
import javafx.scene.control.TextField
import javafx.scene.layout.FlowPane

class PropertyViewWrongId : Component<FlowPane>() {

    val username: TextField by fxid()
}
