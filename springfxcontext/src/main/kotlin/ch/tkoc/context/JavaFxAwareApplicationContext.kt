package ch.tkoc.context

import ch.tkoc.context.annotation.FxView
import ch.tkoc.fx.component.View
import org.springframework.context.annotation.AnnotationConfigApplicationContext


class JavaFxAwareApplicationContext(customConfiguration: Class<*>): AnnotationConfigApplicationContext(customConfiguration, SpringFxConfiguration::class.java) {

    fun findInitialView(): View<*> = getBeansOfType(View::class.java)
            .map{it.value}
            .filter { it.javaClass.findAnnotation<FxView>()?.initial ?: false }
            .first()
}