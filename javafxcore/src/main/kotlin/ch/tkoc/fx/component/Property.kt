package ch.tkoc.fx.component

import javafx.scene.Node
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

class Property<E: Node>(val id: String?, val type: KClass<E>) {

    operator fun getValue(uiView: View<*>, property: KProperty<*>): E = uiView.findElementById(id ?: property.name, type)
}