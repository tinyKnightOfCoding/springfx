package ch.tkoc.springfx.context.annotation

import org.springframework.stereotype.Component
import java.net.URL


@Component
annotation class FxComponent(val filename: String = "")

fun FxComponent.findLocation(type: Class<*>) : URL = type.getResource("${if(filename.isEmpty()) type.simpleName else filename}.fxml")