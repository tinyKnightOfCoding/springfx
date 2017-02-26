package ch.tkoc.component

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