package ch.tkoc.shuffle.user

import ch.tkoc.shuffle.user.model.RegisterRequest
import ch.tkoc.shuffle.user.service.UserDataService
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.SpyBean
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserInfoIntegrationTest {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @SpyBean
    lateinit var userDataService: UserDataService

    @Test
    fun unauthorized() {
        val response = restTemplate.getForEntity("/users/foo@bar.com", String::class.java)
        assertEquals(HttpStatus.UNAUTHORIZED, response.statusCode)
    }

    @Test
    @DirtiesContext
    fun authorized() {
        userDataService.create(RegisterRequest("aUser", "foo@bar.com", "password1"))
        val response = restTemplate.exchange("/users/aUser", HttpMethod.GET, HttpEntity<Any>(createHeaders("aUser", "password1")), String::class.java)
        assertEquals(HttpStatus.OK, response.statusCode)
    }

    @Test
    @DirtiesContext
    fun userNotFound() {
        userDataService.create(RegisterRequest("aUser", "foo@bar.com", "password1"))
        val response = restTemplate.exchange("/users/aUser2", HttpMethod.GET, HttpEntity<Any>(createHeaders("aUser", "password1")), String::class.java)
        assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
    }
}