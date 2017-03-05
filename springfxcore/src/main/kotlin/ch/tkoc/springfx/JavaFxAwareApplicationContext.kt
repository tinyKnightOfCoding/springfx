package ch.tkoc.springfx

import org.springframework.context.annotation.AnnotationConfigApplicationContext


class JavaFxAwareApplicationContext(vararg annotationClasses: Class<*>) : AnnotationConfigApplicationContext(*annotationClasses, SpringFxBaseConfiguration::class.java)