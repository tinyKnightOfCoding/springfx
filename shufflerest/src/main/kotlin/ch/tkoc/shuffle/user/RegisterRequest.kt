package ch.tkoc.shuffle.user

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User

data class RegisterRequest(val username: String = "", val email: String = "", val password: String = "") {

    fun createUserData() : UserData = UserData(User(username, password, listOf<GrantedAuthority>()), email)
}