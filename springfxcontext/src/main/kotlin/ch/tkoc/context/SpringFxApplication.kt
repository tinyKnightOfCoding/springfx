package ch.tkoc.context

import ch.tkoc.context.annotation.FxView
import ch.tkoc.fx.component.View
import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
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

    lateinit var appContext: ApplicationContext
    lateinit var stage: Stage

    override fun start(primaryStage: Stage) {
        stage = primaryStage
        appContext = AnnotationConfigApplicationContext(configurationClass!!.java, SpringFxConfiguration::class.java)
        renderInitialView()
    }

    private fun renderInitialView() {
        val initialView = findInitialView()
        render(initialView)
    }

    fun render(view: View<*>) = stage.apply {
        scene = Scene(view.root)
        show()
    }

    fun findInitialView(): View<*> = appContext.getBeansOfType(View::class.java)
            .map{it.value}
            .filter { it.javaClass.findAnnotation<FxView>()?.initial ?: false }
            .first()
}

