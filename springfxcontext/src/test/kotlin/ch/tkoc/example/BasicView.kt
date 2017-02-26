package ch.tkoc.example

import ch.tkoc.context.annotation.FxView
import ch.tkoc.fx.View
import javafx.scene.layout.Pane

@FxView(initial = true)
class BasicView : View<Pane>()