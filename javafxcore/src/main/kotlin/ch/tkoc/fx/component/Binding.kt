package ch.tkoc.fx.component

import kotlin.reflect.KProperty

interface Binding<T> {

    operator fun getValue(uiView: View<*>, property: KProperty<*>): T

    operator fun setValue(uiView: View<*>, property: KProperty<*>, value: T)
}