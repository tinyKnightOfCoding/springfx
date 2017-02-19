package ch.tkoc.fx.component

import javafx.scene.control.TextField
import kotlin.reflect.KProperty

class TextFieldBinding(val id: String?): Binding<String> {

    override fun getValue(uiComponent: Component<*>, property: KProperty<*>): String = findTextField(property, uiComponent).text

    override fun setValue(uiComponent: Component<*>, property: KProperty<*>, value: String) {
        findTextField(property, uiComponent).text = value
    }

    private fun findTextField(property: KProperty<*>, uiComponent: Component<*>) = uiComponent.findElementById(id ?: property.name, TextField::class)
}
