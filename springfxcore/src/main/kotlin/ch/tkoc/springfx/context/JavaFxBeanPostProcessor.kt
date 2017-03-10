package ch.tkoc.springfx.context

import ch.tkoc.springfx.context.annotation.FxmlConfig
import ch.tkoc.springfx.context.annotation.NoFxml
import ch.tkoc.springfx.util.findAnnotation
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.util.BuilderFactory
import org.springframework.beans.factory.config.BeanPostProcessor
import java.net.URL


class JavaFxBeanPostProcessor(val builderFactory: BuilderFactory) : BeanPostProcessor {

    private fun Parent.findLocation(): URL = javaClass.findAnnotation<FxmlConfig>()?.let {
        javaClass.getResource(it.filename)
    } ?: javaClass.getResource("${javaClass.simpleName}.fxml")

    override fun postProcessBeforeInitialization(bean: Any, beanName: String?): Any = when (bean) {
        is Parent -> processFxComponent(bean)
        else -> bean
    }

    fun processFxComponent(fxComponent: Parent): Parent {
        fxComponent.loadFxmlIfNotAnnotated()
        return fxComponent
    }

    private fun Parent.loadFxmlIfNotAnnotated() {
        if (javaClass.findAnnotation<NoFxml>() == null) {
            loadFxml()
        }
    }


    fun Parent.loadFxml() = FXMLLoader().apply {
        location = this@loadFxml.findLocation()
        builderFactory = this@JavaFxBeanPostProcessor.builderFactory
        setRoot(this@loadFxml)
        load()
    }
}