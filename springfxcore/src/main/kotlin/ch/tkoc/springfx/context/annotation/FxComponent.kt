package ch.tkoc.springfx.context.annotation

import ch.tkoc.springfx.context.CustomAwareBuilder
import javafx.util.Builder
import org.springframework.stereotype.Component
import java.net.URL
import kotlin.reflect.KClass


@Component
annotation class FxComponent(val filename: String = "", val builderClass: KClass<*> = CustomAwareBuilder::class)

fun FxComponent.findLocation(type: Class<*>) : URL = type.getResource("${if(filename.isEmpty()) type.simpleName else filename}.fxml")