package ch.tkoc.springfx.util

import javafx.scene.Node

inline fun <reified N: Node> Node.lookup(cssSelector: String) : N? = lookup(cssSelector).let {
    if(it is N) {
        return it
    }
    return null
}
