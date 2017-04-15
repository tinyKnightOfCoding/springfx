package ch.tkoc.shuffle.user

import ch.tkoc.shuffle.user.model.RegisterRequest
import ch.tkoc.shuffle.user.service.UserDataService
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import org.apache.tomcat.util.codec.binary.Base64
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.SpyBean
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit4.SpringRunner
import java.nio.charset.Charset

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RegistrationIntegrationTest {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @SpyBean
    lateinit var userDetailsService: UserDetailsService

    @Test
    fun userDetailsServiceIsUserDataService() {
        assertTrue(userDetailsService is UserDataService)
    }

    @Test
    @DirtiesContext
    fun createUser() {
        val response = restTemplate.postForEntity("/users", RegisterRequest("newUser", "foo@bar.com", "password1"), String::class.java)
        assertEquals(HttpStatus.CREATED, response.statusCode)
        verify(userDetailsService as UserDataService).create(any())
    }

    @Test
    fun usernameTooShort() {
        val response = restTemplate.postForEntity("/users", RegisterRequest("use", "foo@bar.com", "password1"), String::class.java)
        assertEquals(HttpStatus.BAD_REQUEST, response.statusCode)
        assertTrue(response.body.contains("username.invalid"))
    }

    @Test
    @DirtiesContext
    fun notAnonymous() {
        (userDetailsService as UserDataService).create(RegisterRequest("aUser", "foo@bar.com", "password1"))
        val response = restTemplate.exchange("/users", HttpMethod.POST, HttpEntity<Any>(createHeaders("aUser", "password1")), String::class.java)
        assertEquals(HttpStatus.FORBIDDEN, response.statusCode)
        verify(userDetailsService).loadUserByUsername("aUser")
    }

    @Test
    fun notAnonymousUnknownUser() {
        val response = restTemplate.exchange("/users", HttpMethod.POST, HttpEntity<Any>(createHeaders("aUser", "password1")), String::class.java)
        assertEquals(HttpStatus.UNAUTHORIZED, response.statusCode)
        verify(userDetailsService).loadUserByUsername("aUser")
    }
}

fun createHeaders(username: String, password: String) = HttpHeaders().apply {
    val auth = "$username:$password"
    val encodedAuth = Base64.encodeBase64(auth.toByteArray(Charset.forName("US-ASCII")))
    val authHeaderValue = "Basic ${String(encodedAuth)}"
    set("Authorization", authHeaderValue)
    set("Content-Type", "application/json; charset=utf8")
}
