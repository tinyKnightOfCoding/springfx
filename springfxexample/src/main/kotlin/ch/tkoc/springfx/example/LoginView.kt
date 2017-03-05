package ch.tkoc.springfx.example

import ch.tkoc.springfx.context.annotation.FxComponent
import ch.tkoc.springfx.context.annotation.FxView
import javafx.scene.layout.FlowPane

@FxView(initial = true)
@FxComponent
class LoginView : FlowPane()