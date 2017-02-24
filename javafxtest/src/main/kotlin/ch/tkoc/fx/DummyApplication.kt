package ch.tkoc.fx

import javafx.application.Application
import javafx.stage.Stage


var running = false

fun launchDummyApplication() {
    if(running) {
        return
    }
    Thread {
        run{
            running = true
            Application.launch(DummyApplication::class.java)
        }
    }.start()
}

class DummyApplication: Application() {

    override fun start(primaryStage: Stage?) { }

}
