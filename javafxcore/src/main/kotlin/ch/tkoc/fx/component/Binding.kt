package ch.tkoc.fx.component

import kotlin.reflect.KProperty

interface Binding<T> {

    operator fun getValue(uiComponent: Component<*>, property: KProperty<*>): T

    operator fun setValue(uiComponent: Component<*>, property: KProperty<*>, value: T)
}