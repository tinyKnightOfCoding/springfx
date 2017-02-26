package ch.tkoc.component

import ch.tkoc.context.annotation.FxComponent
import javafx.scene.layout.FlowPane
import org.springframework.beans.factory.annotation.Autowired

@FxComponent
class LoginForm @Autowired constructor(val loginService: LoginService) : FlowPane()