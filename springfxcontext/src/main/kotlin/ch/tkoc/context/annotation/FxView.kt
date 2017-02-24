package ch.tkoc.context.annotation

import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Scope("view")
@Component
annotation class FxView(val initial: Boolean = false)
