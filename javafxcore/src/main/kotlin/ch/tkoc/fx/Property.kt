package ch.tkoc.fx

import javafx.scene.Node
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

class Property<E: Node>(val id: String?, val type: KClass<E>) {

    operator fun getValue(view: View<*>, property: KProperty<*>): E = lookUp(view.root, toSelector(property))

    operator fun getValue(node: Node, property: KProperty<*>): E = lookUp(node, toSelector(property))

    fun lookUp(node: Node, cssSelector: String) = node.lookup(cssSelector, type.java) ?:
                    throw IllegalArgumentException("Element $cssSelector could not be found in namespace.")

    fun toSelector(property: KProperty<*>) = "#${id ?: property.name}"
}