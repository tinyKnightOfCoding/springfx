package ch.tkoc.shuffle.user.model

import org.springframework.security.crypto.password.PasswordEncoder

data class RegisterRequest(val username: String = "", val email: String = "", val password: String = "") {

    fun createUserData(passwordEncoder: PasswordEncoder? = null) : UserData = UserData(username, encodedPassword(passwordEncoder) , email)

    fun encodedPassword(passwordEncoder: PasswordEncoder?) : String {
        if (passwordEncoder == null) {
            return password
        }
        return passwordEncoder.encode(password)
    }

}