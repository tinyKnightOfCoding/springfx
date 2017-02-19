package ch.tkoc.fx.component

import javafx.fxml.FXMLLoader
import javafx.scene.Node
import java.net.URL
import kotlin.reflect.KClass

/**
 * a class that corresponds to an fxml file.
 */
open class UiComponent<out T : Node> {

    val fxmlLocation: URL = javaClass.getResource("${javaClass.simpleName}.fxml") ?: throw IllegalStateException("could not find fxml for $javaClass")

    val fxmlLoader: FXMLLoader = FXMLLoader(fxmlLocation).apply {
        setController(this@UiComponent)
    }

    val root: T = fxmlLoader.load()

    fun <E : Node> findElementById(id: String, type: KClass<E>): E {
        fxmlLoader.namespace[id]?.let { element ->
            when (element) {
                type.java.isInstance(element) -> return element as E
                else -> throw IllegalArgumentException("Element $id could not be assigned to $type.")
            }
        }
        throw IllegalArgumentException("Element $id could not be found in namespace.")
    }

    inline fun <reified P : Node> fxid(id: String? = null) = UiProperty(id, P::class)
}