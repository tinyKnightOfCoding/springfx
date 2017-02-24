package ch.tkoc.context.example

import ch.tkoc.context.launch
import org.springframework.context.annotation.Configuration


fun main(vararg args: String) {
    launch(BasicConfiguration::class)
}

@Configuration
//no package scan, because we are in range of SpringFxConfiguration
class BasicConfiguration