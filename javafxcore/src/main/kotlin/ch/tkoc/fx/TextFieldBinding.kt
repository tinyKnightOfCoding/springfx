package ch.tkoc.fx

import javafx.scene.control.TextField
import kotlin.reflect.KProperty

class TextFieldBinding(val id: String?): Binding<String> {

    override fun getValue(uiView: View<*>, property: KProperty<*>): String = findTextField(uiView, property).text

    override fun setValue(uiView: View<*>, property: KProperty<*>, value: String) {
        findTextField(uiView, property).text = value
    }

    private fun findTextField(uiView: View<*>, property: KProperty<*>) = uiView.root.lookup("#${id ?: property.name}", TextField::class.java) ?:
            throw IllegalArgumentException("Could not find Element #${id ?: property.name} of type Button.")
}
