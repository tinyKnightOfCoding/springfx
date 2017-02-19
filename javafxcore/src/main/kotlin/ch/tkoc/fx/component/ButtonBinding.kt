package ch.tkoc.fx.component

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Button
import kotlin.reflect.KProperty

class ButtonBinding(val id: String?) : Binding<(ActionEvent) -> Unit> {

    override fun getValue(uiComponent: Component<*>, property: KProperty<*>): (ActionEvent) -> Unit = {event: ActionEvent ->
        findButton(property, uiComponent).onAction?.handle(event)
    }

    override fun setValue(uiComponent: Component<*>, property: KProperty<*>, value: (ActionEvent) -> Unit) {
        findButton(property, uiComponent).onAction = EventHandler<ActionEvent> { event -> value(event) }
    }

    private fun findButton(property: KProperty<*>, uiComponent: Component<*>) = uiComponent.findElementById(id ?: property.name, Button::class)
}
