package ch.tkoc.springfx.context

import ch.tkoc.springfx.context.annotation.FxmlConfig
import ch.tkoc.springfx.util.findAnnotation
import javafx.scene.Node
import javafx.scene.Parent
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
            appContext.getBeanNamesForType(Parent::class.java)
                    .map(appContext::getType).forEach { put(it, findBuilderForType(it)) }
        }
    }

    private fun findBuilderForType(type: Class<*>) = findConfiguredBuilder(type) ?: JavaFxBeanBuilder::class.java

    private fun findConfiguredBuilder(type: Class<*>) : Class<JavaFxBeanBuilder>? = type.findAnnotation<FxmlConfig>()?.builderType?.let {
        if(JavaFxBeanBuilder::class.java.isAssignableFrom(it.java)) {
            return it.java as Class<JavaFxBeanBuilder>
        }
        throw IllegalArgumentException("builder in FxmlConfig annotation must be a subtype of JavaFxBeanBuilder")
    }


    override fun getBuilder(type: Class<*>?): Builder<*>? = builderContext[type]?.newInstance()?.apply {
        bean = appContext.getBean(type) as Node
    }
}