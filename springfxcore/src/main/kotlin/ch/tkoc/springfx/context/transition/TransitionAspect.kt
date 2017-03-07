package ch.tkoc.springfx.context.transition

import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut

@Aspect
class TransitionAspect {

    @Pointcut("execution(@ch.tkoc.springfx.context.annotation.FxTransition kotlin.String *(..))")
    fun transitionPointcut() {}

    @AfterReturning(pointcut = "transitionPointcut()", returning = "code")
    fun doTransition(code: String) {}
}