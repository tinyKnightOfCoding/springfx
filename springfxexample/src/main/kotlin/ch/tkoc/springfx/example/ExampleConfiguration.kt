package ch.tkoc.springfx.example

import ch.tkoc.springfx.launch
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Configuration

@Configuration
class ExampleConfiguration

fun main(vararg args: String) {
    launch(AnnotationConfigApplicationContext(ExampleConfiguration::class.java))
}