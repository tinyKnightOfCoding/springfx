package ch.tkoc.springfx.context

import ch.tkoc.springfx.context.annotation.FxmlConfig
import javafx.scene.layout.FlowPane

@FxmlConfig(filename = "LoginForm.fxml")
class DifferentLoginForm : FlowPane()