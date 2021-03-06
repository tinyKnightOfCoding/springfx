package ch.tkoc.springfx

import ch.tkoc.springfx.context.annotation.TransitionIn
import ch.tkoc.springfx.context.annotation.TransitionOut
import ch.tkoc.springfx.context.annotation.View
import ch.tkoc.springfx.util.findAnnotation
import javafx.application.Application
import javafx.application.Platform
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import org.springframework.core.annotation.AnnotationUtils


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

    fun lookupView(beanName: String) : Parent = applicationContext.getBean(beanName, Parent::class.java)


    fun Parent.invokeTransitionIn() = invokeMethodsWithAnnotation<TransitionIn>()

    fun Parent.invokeTransitionOut() = invokeMethodsWithAnnotation<TransitionOut>()

    inline fun <reified A : Annotation> Parent.invokeMethodsWithAnnotation() = javaClass.methods.filter { it.findAnnotation<A>() != null && it.parameterCount == 0 }.forEach { it.invoke(this) }


    override fun exit() = Platform.exit()
}