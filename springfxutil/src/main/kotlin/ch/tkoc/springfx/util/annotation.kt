package ch.tkoc.springfx.util

import org.springframework.core.annotation.AnnotationUtils
import java.lang.reflect.Method


inline fun <reified A: Annotation> Class<*>.findAnnotation() : A? = AnnotationUtils.findAnnotation(this, A::class.java)

inline fun <reified A: Annotation> Method.findAnnotation() : A? = AnnotationUtils.findAnnotation(this, A::class.java)