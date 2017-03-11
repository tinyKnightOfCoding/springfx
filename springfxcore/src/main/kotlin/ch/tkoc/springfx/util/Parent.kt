package ch.tkoc.springfx.util

import ch.tkoc.springfx.context.annotation.FxmlConfig
import ch.tkoc.springfx.context.annotation.NoFxml
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.util.BuilderFactory
import java.net.URL

fun Parent.loadFxmlIfNotAnnotated(builderFactory: BuilderFactory? = null) {
    if (javaClass.findAnnotation<NoFxml>() == null) {
        loadFxml(builderFactory)
    }
}

fun Parent.loadFxml(builderFactory: BuilderFactory? = null) = FXMLLoader().apply {
    location = findLocation()
    this.builderFactory = builderFactory
    setRoot(this)
    load()
}

private fun Parent.findLocation(): URL = javaClass.findAnnotation<FxmlConfig>()?.let {
    javaClass.getResource(it.filename)
} ?: javaClass.getResource("${javaClass.simpleName}.fxml")


