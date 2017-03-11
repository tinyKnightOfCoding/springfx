package ch.tkoc.springfx.example

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.scene.layout.FlowPane
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Scope("springfx.view")
@Component
class DataFilter @Autowired constructor(val dataController: DataController) : FlowPane() {

    val textField : TextField get() = lookup("#filterText") as TextField

    val button : Button get() = lookup("#submit") as Button

    @PostConstruct
    fun onShow() {
        button.onAction = EventHandler<ActionEvent> {
            dataController.filter(textField.text)
        }
    }
}
