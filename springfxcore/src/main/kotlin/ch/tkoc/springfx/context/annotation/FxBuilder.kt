package ch.tkoc.springfx.context.annotation

import ch.tkoc.springfx.context.JavaFxBeanBuilder
import kotlin.reflect.KClass


annotation class FxBuilder<T: JavaFxBeanBuilder>(val builderType: KClass<T>)