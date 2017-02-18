package ch.tkoc.fx.component

import javafx.fxml.FXMLLoader
import java.net.URL

/**
 * a class that corresponds to an fxml file.
 */
open class UIComponent {

    val fxmlLocation: URL =  javaClass.getResource("${javaClass.simpleName}.fxml") ?: throw IllegalStateException("could not find fxml for $javaClass")

    val fxmlLoader : FXMLLoader = FXMLLoader(fxmlLocation).apply {
        setController(this)
    }
}