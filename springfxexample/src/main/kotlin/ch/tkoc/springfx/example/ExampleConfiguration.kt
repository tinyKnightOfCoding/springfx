package ch.tkoc.springfx.example

import ch.tkoc.springfx.launch
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan("ch.tkoc.springfx.example")
class ExampleConfiguration

fun main(vararg args: String) {
    launch(ExampleConfiguration::class.java)
}