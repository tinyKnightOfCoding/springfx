package ch.tkoc.springfx.context.scope

import org.springframework.beans.factory.ObjectFactory
import org.springframework.beans.factory.config.Scope
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware


class ViewScope : Scope, ApplicationContextAware {

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

    override fun get(name: String, objectFactory: ObjectFactory<*>): Any = objects.getOrPut(name, { objectFactory.`object` })

    fun removeAll() = objects.clear()
}