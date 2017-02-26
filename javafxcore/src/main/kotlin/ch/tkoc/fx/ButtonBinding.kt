package ch.tkoc.fx

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Button
import kotlin.reflect.KProperty

class ButtonBinding(val id: String?) : Binding<(ActionEvent) -> Unit> {

    override fun getValue(uiView: View<*>, property: KProperty<*>): (ActionEvent) -> Unit = { event: ActionEvent ->
        findButton(uiView, property).onAction?.handle(event)
    }

    override fun setValue(uiView: View<*>, property: KProperty<*>, value: (ActionEvent) -> Unit) {
        findButton(uiView, property).onAction = EventHandler<ActionEvent> { event -> value(event) }
    }

    private fun findButton(uiView: View<*>, property: KProperty<*>) = uiView.root.lookup("#${id ?: property.name}", Button::class.java) ?:
            throw IllegalArgumentException("Could not find Element #${id ?: property.name} of type Button.")
}
