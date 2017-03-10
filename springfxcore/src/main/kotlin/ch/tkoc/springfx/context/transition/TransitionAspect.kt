package ch.tkoc.springfx.context.transition

import ch.tkoc.springfx.SpringFxApplication
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut

@Aspect
class TransitionAspect(val springFxApplication: SpringFxApplication) {

    @Pointcut("execution(@ch.tkoc.springfx.context.annotation.Transition String *(..))")
    fun transitionPointcut() {}

    @AfterReturning(pointcut = "transitionPointcut()", returning = "code")
    fun doTransition(code: String) : Unit = when(code) {
        ":keep" -> {}
        ":exit" -> springFxApplication.exit()
        else -> springFxApplication.showView(code)
    }
}