package ch.tkoc.springfx

import javafx.stage.Stage
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import java.util.function.Supplier


@Suppress("UNCHECKED_CAST")
class JavaFxAwareApplicationContext(val stage: Stage, val applicationContext: AnnotationConfigApplicationContext) : ApplicationContext by applicationContext {

    init {
        applicationContext.registerBean("ch.tkoc.springfx.stage",
                Stage::class.java,
                { stage } as Supplier<Stage>)
        applicationContext.registerBean("ch.tkoc.springfx.springFxBaseConfiguration",
                SpringFxBaseConfiguration::class.java,
                { SpringFxBaseConfiguration()} as Supplier<SpringFxBaseConfiguration>)
        applicationContext.refresh()
    }
}