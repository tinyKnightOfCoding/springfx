package ch.tkoc.shuffle.user

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserRegistrationTest {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Test
    fun createUser() {
        val response = restTemplate.postForEntity("/users", RegisterRequest("foo", "foo@bar.com", "password"), String::class.java)
        println(response.statusCode)
    }

    @Test
    fun createUser_UsernameNotValid() {}

    @Test
    fun createUser_EmailNotValid() {}

    @Test
    fun createUser_PasswordNotValid() {}
}