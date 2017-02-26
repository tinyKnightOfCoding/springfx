package ch.tkoc.context

import javafx.fxml.JavaFXBuilderFactory
import javafx.scene.Node
import javafx.util.Builder
import javafx.util.BuilderFactory
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component

@Component("ch.tkoc.context.contextAwareJavaFxBuilderFactory")
class ContextAwareJavaFxBuilderFactory : BuilderFactory, ApplicationContextAware {

    lateinit var appContext: ApplicationContext
    lateinit var contextBuildables: Set<Class<*>>

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        appContext = applicationContext
        findContextBuildables()
    }

    fun findContextBuildables() {
        contextBuildables = appContext.getBeanNamesForType(Node::class.java)
                .map { appContext.getType(it) }
                .toSet()
    }


    override fun getBuilder(type: Class<*>): Builder<*>? {
        if(contextBuildables.contains(type)) {
            return ContextAwareBuilder(appContext, type)
        }
        return null
    }
}