package ch.tkoc.springfx.util

import ch.tkoc.springfx.context.annotation.FxmlConfig
import ch.tkoc.springfx.context.annotation.NoFxml
import ch.tkoc.springfx.context.annotation.TransitionIn
import ch.tkoc.springfx.context.annotation.TransitionOut
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.util.BuilderFactory
import java.net.URL

fun Parent.loadFxmlIfNotAnnotated(builderFactory: BuilderFactory? = null) {
    if (javaClass.findAnnotation<NoFxml>() == null) {
        loadFxml(builderFactory)
    }
}

fun Parent.loadFxml(customBuilderFactory: BuilderFactory? = null, fxmlLoader: FXMLLoader = FXMLLoader()) = fxmlLoader.apply {
    location = createFxmlUrl()
    if(customBuilderFactory != null) {
        builderFactory = customBuilderFactory
    }
    setRoot(this)
    load()
}

fun Parent.createFxmlUrl(fileNameProvider: () -> String? = { javaClass.findAnnotation<FxmlConfig>()?.filename }): URL = fileNameProvider()?.let {
    return javaClass.getResource(it)
} ?: javaClass.getResource("${javaClass.simpleName}.fxml")

fun Parent.invokeTransitionIn() = invokeMethodsWithAnnotation<TransitionIn>()

fun Parent.invokeTransitionOut() = invokeMethodsWithAnnotation<TransitionOut>()

inline fun <reified A : Annotation> Parent.invokeMethodsWithAnnotation() = findMethodsWithAnnotation<A>().forEach { it.invoke(this) }

//TODO code smell #findMethodsWithAnnotationAndNoArguments
inline fun <reified A : Annotation> Parent.findMethodsWithAnnotation() = javaClass.methods.filter { it.findAnnotation<A>() != null && it.parameterCount == 0 }