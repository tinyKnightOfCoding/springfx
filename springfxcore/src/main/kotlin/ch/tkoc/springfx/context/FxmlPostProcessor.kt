package ch.tkoc.springfx.context

import ch.tkoc.springfx.context.annotation.FxComponent
import ch.tkoc.springfx.context.annotation.findLocation
import ch.tkoc.springfx.util.findAnnotation
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import org.springframework.beans.factory.config.BeanPostProcessor


class FxmlPostProcessor : BeanPostProcessor {

    override fun postProcessBeforeInitialization(bean: Any, beanName: String?): Any =
            bean.javaClass.findAnnotation<FxComponent>()?.let { annotation ->
                if (bean is Node) {
                    loadFxmlForBean(bean, annotation)
                }
            } ?: bean


    fun loadFxmlForBean(bean: Node, fxComponent: FxComponent) = FXMLLoader().apply {
        location = fxComponent.findLocation(bean.javaClass)
        setRoot(bean)
        load()
    }
}