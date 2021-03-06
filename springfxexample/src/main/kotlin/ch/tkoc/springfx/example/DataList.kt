package ch.tkoc.springfx.example

import ch.tkoc.springfx.context.annotation.NoFxml
import javafx.scene.control.ListView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
@Scope("springfx.view")
@NoFxml
class DataList @Autowired constructor(val dataController: DataController) : ListView<String>() {

    @PostConstruct
    fun setUp() {
        items = dataController.data
    }
}
