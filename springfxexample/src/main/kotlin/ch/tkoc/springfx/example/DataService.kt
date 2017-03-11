package ch.tkoc.springfx.example

import org.springframework.stereotype.Component

@Component
class DataService {

    fun data() = listOf("Pikachu", "Bulbasaur", "Charmander", "Squirtle")
}
