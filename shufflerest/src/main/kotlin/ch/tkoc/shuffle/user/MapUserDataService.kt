package ch.tkoc.shuffle.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class MapUserDataService @Autowired constructor(val passwordEncoder: PasswordEncoder) : UserDataService {

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