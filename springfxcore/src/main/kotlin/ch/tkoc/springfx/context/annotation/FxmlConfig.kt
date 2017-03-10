package ch.tkoc.springfx.context.annotation

import ch.tkoc.springfx.context.JavaFxBeanBuilder
import kotlin.reflect.KClass


annotation class FxmlConfig(val builderType: KClass<*> = JavaFxBeanBuilder::class, val filename: String = "")