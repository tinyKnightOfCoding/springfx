package ch.tkoc.springfx.context

import ch.tkoc.springfx.context.annotation.FxBuilder
import ch.tkoc.springfx.context.annotation.FxComponent
import javafx.scene.layout.FlowPane

@FxBuilder<CustomBuilder>(CustomBuilder::class)
@FxComponent(filename = "LoginForm")
class CustomBuilderLoginForm : FlowPane()