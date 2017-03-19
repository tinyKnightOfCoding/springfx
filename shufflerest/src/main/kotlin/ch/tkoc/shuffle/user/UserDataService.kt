package ch.tkoc.shuffle.user

import org.springframework.security.core.userdetails.UserDetailsService


interface UserDataService : UserDetailsService {

    fun create(registerRequest: RegisterRequest)

    fun findFirst(criteria: UserData.() -> Boolean) : UserData
}