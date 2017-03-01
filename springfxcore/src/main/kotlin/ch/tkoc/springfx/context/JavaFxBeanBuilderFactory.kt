package ch.tkoc.springfx.context

import ch.tkoc.springfx.context.annotation.FxBuilder
import ch.tkoc.springfx.context.annotation.FxComponent
import ch.tkoc.springfx.util.findAnnotation
import javafx.scene.Node
import javafx.util.Builder
import javafx.util.BuilderFactory
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware


class JavaFxBeanBuilderFactory : BuilderFactory, ApplicationContextAware {

    lateinit var builderContext: Map<Class<*>, Class<JavaFxBeanBuilder>>
    lateinit var appContext: ApplicationContext

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        appContext = applicationContext
        val tempBuilderContext = mutableMapOf<Class<*>, Class<JavaFxBeanBuilder>>()
        appContext.getBeanNamesForAnnotation(FxComponent::class.java).map { beanName ->
            tempBuilderContext[appContext.getType(beanName)] = appContext.getType(beanName).findAnnotation<FxBuilder<JavaFxBeanBuilder>>()!!.type.java
        }
        builderContext = tempBuilderContext
    }

    override fun getBuilder(type: Class<*>?): Builder<*>? = builderContext[type]?.newInstance()?.apply {
        bean = appContext.getBean(type) as Node
    }
}