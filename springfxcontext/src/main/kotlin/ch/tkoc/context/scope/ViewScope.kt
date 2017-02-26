package ch.tkoc.context.scope

import ch.tkoc.fx.View
import org.springframework.beans.factory.ObjectFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.Scope
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component

//TODO write unit tests, as it gets complicated
@Component("ch.tkoc.context.scope.viewScope")
class ViewScope: Scope, ApplicationContextAware {

    lateinit var appContext: ApplicationContext

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        appContext = applicationContext
    }

    val objects: MutableMap<String, Any> = mutableMapOf()

    override fun resolveContextualObject(key: String): Any? = null

    override fun remove(name: String): Any? = objects.remove(name)

    override fun registerDestructionCallback(name: String, callback: Runnable) {
    }

    override fun getConversationId(): String = "FxView"

    override fun get(name: String, objectFactory: ObjectFactory<*>): Any =objects.getOrPut(name, {getWithConfiguredControllerFactory(objectFactory)})

    private fun getWithConfiguredControllerFactory(objectFactory: ObjectFactory<*>): Any = objectFactory.`object`.let { bean ->
        if(bean is View<*>) {
            println(bean)
            bean.fxmlLoader.setControllerFactory { type ->
                println(type)
                appContext.getBean(type) }
            bean.root
        }
        return bean
    }
}