package ch.tkoc.fx

import javafx.application.Application
import javafx.stage.Stage

fun launchDummyApplication() {
    Thread {
        run{
            Application.launch(DummyApplication::class.java)
        }
    }.start()
}

class DummyApplication: Application() {

    override fun start(primaryStage: Stage?) { }

}
