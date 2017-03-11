package ch.tkoc.springfx.context

import ch.tkoc.springfx.context.annotation.FxmlConfig
import javafx.scene.layout.FlowPane

@FxmlConfig(builderType = MutableMap::class)
class BadBuilderLoginForm: FlowPane()