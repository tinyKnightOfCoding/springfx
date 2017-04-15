package ch.tkoc.shuffle.user.service

import ch.tkoc.shuffle.user.model.RegisterRequest
import ch.tkoc.shuffle.user.model.UserData
import org.springframework.security.core.userdetails.UserDetailsService


interface UserDataService : UserDetailsService {

    fun create(registerRequest: RegisterRequest)

    fun findFirst(criteria: UserData.() -> Boolean) : UserData
}