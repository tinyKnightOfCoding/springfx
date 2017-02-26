package ch.tkoc.fx

import kotlin.reflect.KProperty

interface Binding<T> {

    operator fun getValue(uiView: View<*>, property: KProperty<*>): T

    operator fun setValue(uiView: View<*>, property: KProperty<*>, value: T)
}