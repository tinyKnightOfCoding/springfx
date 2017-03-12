package ch.tkoc.shuffle.user

import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import org.apache.tomcat.util.codec.binary.Base64
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.SpyBean
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.*
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserRegistrationTest {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @SpyBean
    lateinit var userDataService: UserDataService

    private fun createHeaders(username: String, password: String) = HttpHeaders().apply {
        set ("Authorization", "Basic dXNlcjpwYXNzd29yZA==")
    }

    @Test
    fun createUser_UsernameTooShort() {
        val response = restTemplate.postForEntity("/users", RegisterRequest("bet", "foo@bar.com", "password123"), String::class.java)
        assertEquals(HttpStatus.BAD_REQUEST, response.statusCode)
        assertEquals(listOf(MediaType.APPLICATION_JSON_UTF8_VALUE), response.headers["Content-Type"])
        assertTrue(response.body.contains("username.invalid"))
    }

    @Test
    @DirtiesContext
    fun createUser_UserAlreadyExists() {
        val response = restTemplate.postForEntity("/users", RegisterRequest("betterWeather", "foo@bar.com", "password123"), String::class.java)
        assertEquals(HttpStatus.CREATED, response.statusCode)
        val response2 = restTemplate.postForEntity("/users", RegisterRequest("betterWeather", "foo@bar.com", "password123"), String::class.java)
        assertEquals(HttpStatus.CONFLICT, response2.statusCode)
        assertNull(response.body)
    }

    @Test
    @DirtiesContext
    fun createUser() {
        val response = restTemplate.postForEntity("/users", RegisterRequest("betterWeather", "foo@bar.com", "password123"), String::class.java)
        assertEquals(HttpStatus.CREATED, response.statusCode)
        assertNull(response.body)
    }

    @Test
    @DirtiesContext
    fun userCreated() {
        val response = restTemplate.postForEntity("/users", RegisterRequest("betterWeather", "foo@bar.com", "password123"), String::class.java)
        assertEquals(HttpStatus.CREATED, response.statusCode)
        val response2 = restTemplate.exchange("/users/foo@bar.com", HttpMethod.GET, HttpEntity<String>(createHeaders("betterWeather", "password123")), String::class.java)
        assertEquals(HttpStatus.OK, response2.statusCode)
    }

    @Test
    @DirtiesContext
    fun createUser_AlreadyLoggedIn() {
        val response = restTemplate.postForEntity("/users", RegisterRequest("betterWeather", "foo@bar.com", "password123"), String::class.java)
        assertEquals(HttpStatus.CREATED, response.statusCode)
        val response2 = restTemplate.exchange("/users", HttpMethod.POST,
                HttpEntity<RegisterRequest>(RegisterRequest("betterWeather2", "foo2@bar.com", "password123"), createHeaders("betterWeather", "password123")), String::class.java)
        assertNotEquals(HttpStatus.CREATED, response2.statusCode)
    }

    @Test
    fun readUser_NotLoggedIn() {
        val response = restTemplate.getForEntity("/users/foo@bar", String::class.java)
        assertEquals(HttpStatus.UNAUTHORIZED, response.statusCode)
    }

    @Test
    fun loadUserByUsernameCalled() {
        val response2 = restTemplate.exchange("/users/foo@bar.com", HttpMethod.GET, HttpEntity<String>(createHeaders("betterWeather", "password123")), String::class.java)
        verify(userDataService).loadUserByUsername("betterWeather2")
    }

    @Test
    fun readUser() {
        fail()
    }


    @Test
    fun readUser_DifferentUser() {
        fail()
    }
}