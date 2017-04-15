package ch.tkoc.shuffle.user

import ch.tkoc.shuffle.user.model.RegisterRequest
import ch.tkoc.shuffle.user.model.UserData
import ch.tkoc.shuffle.user.service.MapUserDataService
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.springframework.security.crypto.password.PasswordEncoder


class MapUserDataServiceTest {

    lateinit var userDataService : MapUserDataService

    @Before
    fun setUp() {
        userDataService = MapUserDataService(object : PasswordEncoder {
            override fun encode(p0: CharSequence): String = p0.toString()

            override fun matches(p0: CharSequence, p1: String): Boolean = p1.equals(p0)
        })
    }

    @Test(expected = UserNotFoundException::class)
    fun loadByUsernameNotFound() {
        userDataService.loadUserByUsername("aUsername")
    }

    @Test
    fun createThenLoad() {
        userDataService.create(RegisterRequest("username", "foo@bar.com", "password1"))
        val user = userDataService.loadUserByUsername("username")
        assertTrue(user is UserData)
        (user as UserData).apply {
            assertEquals("username", username)
            assertEquals("foo@bar.com", email)
            assertEquals("password1", password)
        }
    }
}