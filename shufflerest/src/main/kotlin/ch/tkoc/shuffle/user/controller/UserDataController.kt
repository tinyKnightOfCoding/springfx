package ch.tkoc.shuffle.user.controller

import ch.tkoc.shuffle.user.UserAlreadyExistsException
import ch.tkoc.shuffle.user.UserNotFoundException
import ch.tkoc.shuffle.user.model.RegisterRequest
import ch.tkoc.shuffle.user.model.UserData
import ch.tkoc.shuffle.user.service.UserDataService
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

    @GetMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    fun readUser(@PathVariable("username") searchUsername: String) : ResponseEntity<UserData> {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userDataService.findFirst { searchUsername == username })
        } catch(e: UserNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
        }
    }
}
