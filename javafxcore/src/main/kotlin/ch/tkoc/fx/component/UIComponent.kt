package ch.tkoc.fx.component

import javafx.fxml.FXMLLoader
import javafx.scene.Node
import java.net.URL
import kotlin.reflect.KProperty

/**
 * a class that corresponds to an fxml file.
 */
open class UIComponent<out T: Node> {

    val fxmlLocation: URL =  javaClass.getResource("${javaClass.simpleName}.fxml")

    val fxmlLoader = FXMLLoader(fxmlLocation)

    val root: T = fxmlLoader.load()

    inline fun <reified P: Node> fxid() = object: UIProperty<P> {

        override fun getValue(uiComponent: UIComponent<*>, property: KProperty<*>): P {
            val value = uiComponent.fxmlLoader.namespace[property.name]
            when(value) {
                null -> throw IllegalArgumentException("Property ${property.name} could not be found in namespace.")
                is P -> return value
                else -> throw IllegalArgumentException("Property ${property.name} could not be assigned to ${property.returnType}")
            }
        }
    }
}