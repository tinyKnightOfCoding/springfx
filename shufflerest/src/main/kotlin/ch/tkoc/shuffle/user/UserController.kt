
package ch.tkoc.shuffle.user

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController()
class UserController {

    @RequestMapping(path= arrayOf("/users"), method = arrayOf(RequestMethod.POST), consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE), produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@RequestBody data: RegisterRequest) = println(data)

    @RequestMapping(path = arrayOf("/users/{email}"), method = arrayOf(RequestMethod.GET), produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    fun readUser(@PathVariable("email") email: String) = println(email)
}
