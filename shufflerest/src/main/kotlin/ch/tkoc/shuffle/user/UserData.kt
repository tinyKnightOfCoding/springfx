package ch.tkoc.shuffle.user

import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails

data class UserData(val user: User, val email: String) : UserDetails by user