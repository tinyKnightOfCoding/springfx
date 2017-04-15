package ch.tkoc.shuffle.user

import ch.tkoc.shuffle.user.service.MapUserDataService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class UserManagementConfiguration {

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

    @Bean
    fun userDetailsService(@Autowired passwordEncoder: PasswordEncoder) = MapUserDataService(passwordEncoder)
}
