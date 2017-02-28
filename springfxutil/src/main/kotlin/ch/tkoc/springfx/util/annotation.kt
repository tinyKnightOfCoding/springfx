package ch.tkoc.springfx.util

import org.springframework.core.annotation.AnnotationUtils


inline fun <reified A: Annotation> Class<*>.findAnnotation() : A? = AnnotationUtils.findAnnotation(this, A::class.java)