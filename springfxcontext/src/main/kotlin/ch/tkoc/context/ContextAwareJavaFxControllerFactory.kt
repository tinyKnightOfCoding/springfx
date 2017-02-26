package ch.tkoc.context

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component

//TODO unused?
@Component("ch.tkoc.context.contextAwareJavaFxControllerFactor")
class ContextAwareJavaFxControllerFactory : ApplicationContextAware {

    lateinit var appContext: ApplicationContext

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        appContext = applicationContext
    }

    fun getBean(type: Class<*>) = appContext.getBean(type)
}