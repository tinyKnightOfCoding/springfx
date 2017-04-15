package ch.tkoc.shuffle

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootApplication
class ShuffleRestApplication {
    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()
}

fun main(args: Array<String>) {
    SpringApplication.run(ShuffleRestApplication::class.java, *args)
}
