package ch.tkoc.springfx.example

import javafx.collections.FXCollections
import javafx.collections.ObservableList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Scope("springfx.view")
@Component
class DataController @Autowired constructor(val dataService: DataService) {

    val data : ObservableList<String> = FXCollections.observableArrayList()

    @PostConstruct
    fun setUp() {
        data.setAll(dataService.data())
    }

    fun filter(regex: String) : Unit {
        regex.toRegex().apply {
            data.setAll(dataService.data().filter{ matches(it) })
        }
    }
}
