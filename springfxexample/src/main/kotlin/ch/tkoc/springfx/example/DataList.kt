package ch.tkoc.springfx.example

import ch.tkoc.springfx.context.annotation.NoFxml
import javafx.scene.control.ListView
import org.springframework.stereotype.Component

@Component
@NoFxml
class DataList : ListView<String>()
