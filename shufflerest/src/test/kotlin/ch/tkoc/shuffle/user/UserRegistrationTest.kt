package ch.tkoc.shuffle.user

import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserRegistrationTest {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

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
    fun createUser_AlreadyLoggedIn() {
        fail()
    }

    @Test
    fun readUser_NotLoggedIn() {
        fail()
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