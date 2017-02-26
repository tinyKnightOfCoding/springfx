package ch.tkoc.context

import ch.tkoc.context.scope.ViewScope
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component("ch.tkoc.context.springFx")
class SpringFx @Autowired constructor(val viewScope: ViewScope) {

    lateinit var springFxApplication : SpringFxApplication

    fun transition(beanName: String) {
        viewScope.removeAll()
        springFxApplication.renderBean(beanName)
    }
}