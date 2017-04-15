package ch.tkoc.shuffle.user.service

import ch.tkoc.shuffle.user.UserAlreadyExistsException
import ch.tkoc.shuffle.user.UserNotFoundException
import ch.tkoc.shuffle.user.model.RegisterRequest
import ch.tkoc.shuffle.user.model.UserData
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder

//TODO this class is open because it needs to be mocked. find way around it
open class MapUserDataService(val passwordEncoder: PasswordEncoder) : UserDataService {

    val users = mutableSetOf<UserData>()

    override fun loadUserByUsername(username: String): UserDetails = findFirst { this.username == username }

    override fun create(registerRequest: RegisterRequest) {
        if(users.filter { registerRequest.username == it.username || registerRequest.email == it.email }.isNotEmpty()) {
            throw UserAlreadyExistsException(registerRequest)
        }
        users.add(registerRequest.createUserData(passwordEncoder))
    }

    override fun findFirst(criteria: UserData.() -> Boolean) : UserData = users.filter{it.criteria()}.let {
        if(it.isEmpty()) {
            throw UserNotFoundException()
        }
        return it.first()
    }
}