package ch.tkoc.fx

import javafx.scene.Node


fun <E : Node> Node.lookup(cssSelector: String, type: Class<E>): E? = lookup(cssSelector)?.let {
    if (type.isInstance(it)) {
        return it as E
    }
    throw IllegalArgumentException("Element $cssSelector could not be assigned to $type.")
}

fun textfield(id: String? = null) = TextFieldBinding(id)

fun button(id: String? = null) = ButtonBinding(id)

inline fun <reified P : Node> fxid(id: String? = null) = Property(id, P::class)