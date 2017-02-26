package ch.tkoc.context

import ch.tkoc.fx.View
import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage
import org.springframework.core.annotation.AnnotationUtils
import kotlin.reflect.KClass

//TODO refactor to separate file
inline fun <reified A: Annotation> Class<*>.findAnnotation(): A? = AnnotationUtils.findAnnotation(this, A::class.java)

inline fun <reified A: Annotation> KClass<*>.findAnnotation(): A? = AnnotationUtils.findAnnotation(this.java, A::class.java)

fun launch(customConfiguration: KClass<*>) {
    configurationClass = customConfiguration
    Application.launch(SpringFxApplication::class.java)
}

private var configurationClass: KClass<*>? = null

class SpringFxApplication : Application() {

    lateinit var appContext: JavaFxAwareApplicationContext
    lateinit var stage: Stage

    override fun start(primaryStage: Stage) {
        stage = primaryStage
        appContext = JavaFxAwareApplicationContext(configurationClass!!.java)
        renderInitialView()
    }

    private fun renderInitialView() = render(appContext.findInitialView())

    fun render(view: View<*>) = stage.apply {
        scene = Scene(view.root)
        show()
    }
}

