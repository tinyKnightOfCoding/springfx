package ch.tkoc.fx

import javafx.scene.Node
import javafx.scene.control.TextField
import kotlin.reflect.KProperty

class TextFieldBinding(val id: String?): Binding<String> {

    override fun getValue(view: View<*>, property: KProperty<*>): String = getValue(view.root, property)

    override fun getValue(node: Node, property: KProperty<*>): String = findTextField(node, property).text

    override fun setValue(view: View<*>, property: KProperty<*>, value: String) = setValue(view.root, property, value)

    override fun setValue(node: Node, property: KProperty<*>, value: String) {
        findTextField(node, property).text = value
    }

    private fun findTextField(node: Node, property: KProperty<*>) = node.lookup("#${id ?: property.name}", TextField::class.java) ?:
            throw IllegalArgumentException("Could not find Element #${id ?: property.name} of type Button.")
}
