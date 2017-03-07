package ch.tkoc.springfx

import javafx.application.Application
import javafx.application.Platform
import javafx.stage.Stage


fun launch(configurationClass: Class<*>) {
    if(customConfigClass == null) {
        customConfigClass = configurationClass
        Application.launch(SpringFxBaseApplication::class.java)
    }
}

private var customConfigClass : Class<*>? = null
internal var springFxApplication: SpringFxApplication? = null

class SpringFxBaseApplication : Application(), SpringFxApplication {

    lateinit var stage: Stage

    override fun start(primaryStage: Stage) : Unit = customConfigClass!!.let {
        stage = primaryStage
        JavaFxAwareApplicationContext(it)
    }

    override fun showView(beanName: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun exit() = Platform.exit()
}