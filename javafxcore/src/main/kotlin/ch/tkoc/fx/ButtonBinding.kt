package ch.tkoc.fx

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Node
import javafx.scene.control.Button
import javax.swing.Action
import kotlin.reflect.KProperty

class ButtonBinding(val id: String?) : Binding<(ActionEvent) -> Unit> {

    override fun getValue(view: View<*>, property: KProperty<*>): (ActionEvent) -> Unit = getValue(view.root, property)

    override fun getValue(node: Node, property: KProperty<*>): (ActionEvent) -> Unit = {
        event: ActionEvent -> findButton(node, property).onAction.handle(event)
    }

    override fun setValue(view: View<*>, property: KProperty<*>, value: (ActionEvent) -> Unit) = setValue(view.root, property, value)

    override fun setValue(node: Node, property: KProperty<*>, value: (ActionEvent) -> Unit) {
        findButton(node, property).onAction = EventHandler<ActionEvent> { event -> value(event) }
    }

    private fun findButton(node: Node, property: KProperty<*>) = node.lookup("#${id ?: property.name}", Button::class.java) ?:
            throw IllegalArgumentException("Could not find Element #${id ?: property.name} of type Button.")
}
