package ch.tkoc.springfx.example

import ch.tkoc.springfx.launch
import org.springframework.context.annotation.Configuration

@Configuration
class ExampleConfiguration

fun main(vararg args: String) {
    launch(ExampleConfiguration::class.java)
}