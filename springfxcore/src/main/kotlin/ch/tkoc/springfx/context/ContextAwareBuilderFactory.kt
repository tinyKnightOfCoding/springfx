package ch.tkoc.springfx.context

import ch.tkoc.springfx.context.annotation.FxComponent
import ch.tkoc.springfx.util.findAnnotation
import javafx.util.Builder
import javafx.util.BuilderFactory
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware


class ContextAwareBuilderFactory : BuilderFactory, ApplicationContextAware {

    lateinit var builderContext: Map<Class<*>, Class<Builder<*>>>
    override fun setApplicationContext(applicationContext: ApplicationContext) {
        val tempBuilderContext = mutableMapOf<Class<*>, Class<*>>()
        applicationContext.getBeanNamesForAnnotation(FxComponent::class.java).map { beanName ->
            applicationContext.getType(beanName).findAnnotation<FxComponent>()!! //TODO complete
        }
    }

    override fun getBuilder(type: Class<*>?): Builder<*> {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}