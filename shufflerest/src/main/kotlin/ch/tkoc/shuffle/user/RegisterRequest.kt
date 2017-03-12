package ch.tkoc.shuffle.user

data class RegisterRequest(val username: String = "", val email: String = "", val password: String = "")