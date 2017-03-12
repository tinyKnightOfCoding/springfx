package ch.tkoc.shuffle.user

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
class MapUserDataService : UserDataService {

    val users = mutableSetOf<UserData>()

    override fun loadUserByUsername(username: String): UserDetails = users.filter { it.username == username }.first()

    override fun create(registerRequest: RegisterRequest) {
        if(users.filter { registerRequest.username == it.username || registerRequest.email == it.email }.isNotEmpty()) {
            throw UserAlreadyExistsException(registerRequest)
        }
        users.add(registerRequest.createUserData())
    }

    override fun findFirst(criteria: UserData.() -> Boolean) = users.filter { it.criteria() }.first()
}