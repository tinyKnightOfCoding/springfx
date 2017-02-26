package ch.tkoc.fx

import javafx.scene.control.TextField
import kotlin.reflect.KProperty

class TextFieldBinding(val id: String?): Binding<String> {

    override fun getValue(uiView: View<*>, property: KProperty<*>): String = findTextField(property, uiView).text

    override fun setValue(uiView: View<*>, property: KProperty<*>, value: String) {
        findTextField(property, uiView).text = value
    }

    private fun findTextField(property: KProperty<*>, uiView: View<*>) = uiView.findElementById(id ?: property.name, TextField::class)
}
