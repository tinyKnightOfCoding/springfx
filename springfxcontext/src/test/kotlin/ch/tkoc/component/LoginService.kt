package ch.tkoc.component

interface LoginService {

    fun setCredentials(username: String, password: String)

    fun isLoggedIn()
}
