package ch.tkoc.springfx.context

import ch.tkoc.springfx.util.loadFxmlIfNotAnnotated
import javafx.scene.Parent
import javafx.util.BuilderFactory
import org.springframework.beans.factory.config.BeanPostProcessor


class JavaFxBeanPostProcessor(val builderFactory: BuilderFactory) : BeanPostProcessor {

    override fun postProcessBeforeInitialization(bean: Any, beanName: String?): Any = when (bean) {
        is Parent -> processFxComponent(bean)
        else -> bean
    }

    fun processFxComponent(fxComponent: Parent): Parent {
        fxComponent.loadFxmlIfNotAnnotated(builderFactory)
        return fxComponent
    }
}