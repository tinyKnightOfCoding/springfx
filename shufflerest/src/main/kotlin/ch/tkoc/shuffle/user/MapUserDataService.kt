package ch.tkoc.shuffle.user

import org.slf4j.LoggerFactory
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
class MapUserDataService : UserDataService {

    val users = mutableSetOf<UserData>()

    override fun loadUserByUsername(username: String): UserDetails {
        val user = users.filter { it.username == username }.first()
        LoggerFactory.getLogger(MapUserDataService::class.java).info(user.toString())
        return user
    }

    override fun create(registerRequest: RegisterRequest) {
        if(users.filter { registerRequest.username == it.username || registerRequest.email == it.email }.isNotEmpty()) {
            throw UserAlreadyExistsException(registerRequest)
        }
        LoggerFactory.getLogger(MapUserDataService::class.java).info(registerRequest.createUserData().toString()  + registerRequest.createUserData().user.password)
        users.add(registerRequest.createUserData())
    }

    override fun findFirst(criteria: UserData.() -> Boolean) = users.filter { it.criteria() }.first()
}