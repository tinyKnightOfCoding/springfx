package ch.tkoc.fx

import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import java.net.URL

/**
 * a class that corresponds to an fxml file.
 */
open class View<out T : Parent> {

    val fxmlLocation: URL = javaClass.getResource("${javaClass.simpleName}.fxml") ?: throw IllegalStateException("could not find fxml for $javaClass")

    val fxmlLoader: FXMLLoader = FXMLLoader(fxmlLocation)

    private var _root: T? = null

    val root: T
        get() {
            if (_root == null) {
                _root = fxmlLoader.load()
            }
            return _root!!
        }
}