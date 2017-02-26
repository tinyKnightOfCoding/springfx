package ch.tkoc.component

import ch.tkoc.context.SpringFx
import ch.tkoc.context.annotation.FxComponent
import ch.tkoc.fx.button
import ch.tkoc.fx.fxid
import javafx.event.ActionEvent
import javafx.scene.control.TextField
import javafx.scene.layout.FlowPane
import org.springframework.beans.factory.annotation.Autowired

@FxComponent
class LoginForm @Autowired constructor(val loginService: LoginService, val springFx: SpringFx) : FlowPane() {

    val email: TextField by fxid()

    var login: (ActionEvent) -> Unit by button()
}