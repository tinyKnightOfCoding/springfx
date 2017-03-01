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
        builderContext = extractBuilderContext()
    }

    private fun extractBuilderContext(): MutableMap<Class<*>, Class<JavaFxBeanBuilder>> {
        return mutableMapOf<Class<*>, Class<JavaFxBeanBuilder>>().apply {
            appContext.getBeanNamesForAnnotation(FxComponent::class.java)
                    .map(appContext::getType).forEach { put(it, findBuilderForType(it)) }
        }
    }

    private fun findBuilderForType(type: Class<*>) : Class<JavaFxBeanBuilder> =
            type.findAnnotation<FxBuilder<JavaFxBeanBuilder>>()?.builderType?.java
            ?: JavaFxBeanBuilder::class.java

    override fun getBuilder(type: Class<*>?): Builder<*>? = builderContext[type]?.newInstance()?.apply {
        bean = appContext.getBean(type) as Node
    }
}