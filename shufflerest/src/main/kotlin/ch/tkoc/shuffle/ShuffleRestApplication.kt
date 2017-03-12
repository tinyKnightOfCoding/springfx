package ch.tkoc.shuffle

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class ShuffleRestApplication

fun main(args: Array<String>) {
    SpringApplication.run(ShuffleRestApplication::class.java, *args)
}
