package ch.tkoc.fx

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Button
import kotlin.reflect.KProperty

class ButtonBinding(val id: String?) : Binding<(ActionEvent) -> Unit> {

    override fun getValue(uiView: View<*>, property: KProperty<*>): (ActionEvent) -> Unit = { event: ActionEvent ->
        findButton(property, uiView).onAction?.handle(event)
    }

    override fun setValue(uiView: View<*>, property: KProperty<*>, value: (ActionEvent) -> Unit) {
        findButton(property, uiView).onAction = EventHandler<ActionEvent> { event -> value(event) }
    }

    private fun findButton(property: KProperty<*>, uiView: View<*>) = uiView.findElementById(id ?: property.name, Button::class)
}
