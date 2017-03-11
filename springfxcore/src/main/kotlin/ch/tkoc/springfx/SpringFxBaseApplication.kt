package ch.tkoc.springfx

import ch.tkoc.springfx.context.annotation.View
import ch.tkoc.springfx.util.invokeTransitionIn
import ch.tkoc.springfx.util.invokeTransitionOut
import javafx.application.Application
import javafx.application.Platform
import javafx.scene.Parent
import javafx.scene.Scene
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
    lateinit var applicationContext: JavaFxAwareApplicationContext
    var currentView: Parent? = null

    fun showInitialView() {
        showView(lookupInitialView())
    }

    private fun lookupInitialView() = applicationContext.getBeanNamesForAnnotation(View::class.java)
                .filter { applicationContext.findAnnotationOnBean(it, View::class.java).initial }
                .first()

    override fun start(primaryStage: Stage) : Unit = customConfigClass!!.let {
        stage = primaryStage
        springFxApplication = this
        applicationContext = JavaFxAwareApplicationContext(it).apply {
            beanDefinitionNames.forEach { println("- $it : ${getType(it)}") }
        }
        showInitialView()
    }

    override fun showView(beanName: String) {
        stage.apply {
            val newView = lookupView(beanName)
            currentView?.invokeTransitionOut()
            currentView = newView
            currentView!!.invokeTransitionIn()
            scene = Scene(currentView)
            show()
        }
    }

    fun lookupView(beanName: String) : Parent = applicationContext.getBean(beanName, Parent::class.java) //TODO application Context

    override fun exit() = Platform.exit()
}