package ch.tkoc.springfx.example

import ch.tkoc.springfx.context.annotation.FxComponent
import ch.tkoc.springfx.context.annotation.FxView
import javafx.scene.layout.FlowPane
import org.springframework.beans.factory.annotation.Autowired

@FxView(initial = true)
@FxComponent
class LoginView @Autowired constructor(val loginController: LoginController): FlowPane()