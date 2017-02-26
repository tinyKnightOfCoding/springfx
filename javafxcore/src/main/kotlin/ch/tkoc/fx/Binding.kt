package ch.tkoc.fx

import javafx.event.ActionEvent
import javafx.scene.Node
import kotlin.reflect.KProperty

interface Binding<T> {

    operator fun getValue(view: View<*>, property: KProperty<*>): T

    operator fun setValue(view: View<*>, property: KProperty<*>, value: T)

    operator fun getValue(node: Node, property: KProperty<*>): T

    operator fun setValue(node: Node, property: KProperty<*>, value: T)
}