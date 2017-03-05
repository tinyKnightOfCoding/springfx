package ch.tkoc.springfx

import javafx.application.Application
import javafx.stage.Stage


fun launch(configurationClass: Class<*>) {
    if(customConfigClass == null) {
        customConfigClass = configurationClass
        Application.launch(SpringFxApplication::class.java)
    }
}

internal var customConfigClass : Class<*>? = null

class SpringFxApplication : Application() {

    override fun start(primaryStage: Stage) : Unit = customConfigClass!!.let {
        JavaFxAwareApplicationContext(it).apply {
            beanDefinitionNames.forEach { println("- $it") }
        }
    }
}