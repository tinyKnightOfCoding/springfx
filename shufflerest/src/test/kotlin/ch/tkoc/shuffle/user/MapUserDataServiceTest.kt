package ch.tkoc.shuffle.user

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


class MapUserDataServiceTest {

    lateinit var userDataService : MapUserDataService

    @Before
    fun setUp() {
        userDataService = MapUserDataService()
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