package ch.tkoc.component

import ch.tkoc.context.launch
import org.mockito.Mockito.mock
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan("ch.tkoc.component")
class LoginConfiguration {

    val loginService: LoginService
        @Bean
        get() = mock(LoginService::class.java)
}

fun main(vararg args: String) {
    launch(LoginConfiguration::class)
}