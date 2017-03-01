package ch.tkoc.springfx.context

import ch.tkoc.springfx.context.annotation.FxComponent
import ch.tkoc.springfx.context.annotation.findLocation
import ch.tkoc.springfx.util.findAnnotation
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.util.BuilderFactory
import org.springframework.beans.factory.config.BeanPostProcessor


class JavaFxBeanPostProcessor(val builderFactory: BuilderFactory) : BeanPostProcessor {

    override fun postProcessBeforeInitialization(bean: Any, beanName: String?): Any =
            bean.javaClass.findAnnotation<FxComponent>()?.let { annotation ->
                if (bean is Node) {
                    loadFxmlForBean(bean, annotation)
                }
            } ?: bean


    fun loadFxmlForBean(bean: Node, fxComponent: FxComponent) = FXMLLoader().apply {
        location = fxComponent.findLocation(bean.javaClass)
        builderFactory = this@JavaFxBeanPostProcessor.builderFactory
        setRoot(bean)
        load()
    }
}