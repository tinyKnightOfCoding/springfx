package ch.tkoc.fx.component

import javafx.scene.Node
import kotlin.reflect.KProperty

interface UIProperty<out T: Node> {

    operator fun getValue(uiComponent: UIComponent<*>, property: KProperty<*>): T
}