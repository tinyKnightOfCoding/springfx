package ch.tkoc.fx.property

import ch.tkoc.fx.View
import javafx.scene.control.TextField
import javafx.scene.layout.FlowPane

class PropertyViewWrongId : View<FlowPane>() {

    val username: TextField by fxid()
}
