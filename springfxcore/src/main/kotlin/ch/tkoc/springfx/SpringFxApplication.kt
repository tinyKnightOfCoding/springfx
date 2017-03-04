package ch.tkoc.springfx

import javafx.application.Application
import javafx.stage.Stage
import org.springframework.context.annotation.AnnotationConfigApplicationContext


fun launch(appContext: AnnotationConfigApplicationContext) {
    if(customAppContext != null) {
        customAppContext = appContext
        Application.launch(SpringFxApplication::class.java)
    }
}

internal var customAppContext : AnnotationConfigApplicationContext? = null

class SpringFxApplication : Application() {

    override fun start(primaryStage: Stage) : Unit = customAppContext!!.let {
        JavaFxAwareApplicationContext(primaryStage, it)
    }
}