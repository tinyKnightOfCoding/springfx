package ch.tkoc.example

import ch.tkoc.context.launch
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration


fun main(vararg args: String) {
    launch(BasicConfiguration::class)
}

@Configuration
@ComponentScan("ch.tkoc.example")
class BasicConfiguration