package ch.tkoc.shuffle.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/users", produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE), consumes = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE))
class UserDataController @Autowired constructor(val userDataService: UserDataService) {

    @PostMapping
    fun createUser(@RequestBody @Valid data: RegisterRequest) : ResponseEntity<String> {
        try {
            userDataService.create(data)
            return ResponseEntity.status(HttpStatus.CREATED).body(null)
        } catch (e: UserAlreadyExistsException) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null)
        }
    }

    @InitBinder
    fun addValidator(webDataBinder: WebDataBinder) {
        webDataBinder.validator = RegisterRequestValidator()
    }

    @GetMapping("/{email}")
    @ResponseStatus(HttpStatus.OK)
    fun readUser(@PathVariable("email") email: String) = println(email)
}
