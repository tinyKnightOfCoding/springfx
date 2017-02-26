package ch.tkoc.context

import javafx.util.Builder
import org.springframework.context.ApplicationContext


class ContextAwareBuilder(val appContext: ApplicationContext, val type: Class<*>) : Builder<Any> {

    override fun build() : Any = appContext.getBean(type)
}