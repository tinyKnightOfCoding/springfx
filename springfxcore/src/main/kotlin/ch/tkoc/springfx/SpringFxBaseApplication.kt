package ch.tkoc.springfx

import ch.tkoc.springfx.context.annotation.FxView
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

    fun showInitialView() {
        showView(findInitialViewName())
    }

    private fun findInitialViewName() = applicationContext.getBeanNamesForAnnotation(FxView::class.java)
                .filter { applicationContext.findAnnotationOnBean(it, FxView::class.java).initial }
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
            scene = Scene(applicationContext.getBean(beanName, Parent::class.java))
            show()
        }
    }

    override fun exit() = Platform.exit()
}