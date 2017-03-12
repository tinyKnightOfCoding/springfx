package ch.tkoc.shuffle.user

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/users", produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE), consumes = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE))
class UserDataController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@RequestBody @Valid data: RegisterRequest) {}

    @InitBinder
    fun addValidator(webDataBinder: WebDataBinder) {
        webDataBinder.validator = RegisterRequestValidator()
    }

    @GetMapping("/{email}")
    @ResponseStatus(HttpStatus.OK)
    fun readUser(@PathVariable("email") email: String) = println(email)
}
