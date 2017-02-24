package ch.tkoc.context.scope

import org.springframework.beans.factory.ObjectFactory
import org.springframework.beans.factory.config.Scope
import org.springframework.stereotype.Component

@Component("ch.tkoc.context.scope.viewScope")
class ViewScope: Scope {

    private val objects: MutableMap<String, Any> = mutableMapOf()

    override fun resolveContextualObject(key: String): Any? = null

    override fun remove(name: String): Any? = objects.remove(name)

    override fun registerDestructionCallback(name: String, callback: Runnable) {
    }

    override fun getConversationId(): String = "FxView"

    override fun get(name: String, objectFactory: ObjectFactory<*>): Any = objects.getOrPut(name, {objectFactory.`object`})
}