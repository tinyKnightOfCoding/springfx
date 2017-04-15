package ch.tkoc.shuffle.user

import org.junit.Assert
import org.junit.Assert.assertNotEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.SpyBean
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PasswordHashingIntegrationTest {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @SpyBean
    lateinit var userDetailsService: UserDetailsService

    @Test
    @DirtiesContext
    fun passwordIsNotPlain() {
        val response = restTemplate.postForEntity("/users", RegisterRequest("newUser", "foo@bar.com", "password1"), String::class.java)
        val user = userDetailsService.loadUserByUsername("newUser")
        assertNotEquals("password1", user.password)
    }
}