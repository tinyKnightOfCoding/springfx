package ch.tkoc.springfx.example

import ch.tkoc.springfx.context.annotation.View
import javafx.scene.layout.GridPane
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@View
@Scope("springfx.view")
@Component
class DataView : GridPane()
