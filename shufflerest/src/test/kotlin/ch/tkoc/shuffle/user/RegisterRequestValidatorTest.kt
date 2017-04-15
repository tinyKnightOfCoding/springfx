package ch.tkoc.shuffle.user

import ch.tkoc.shuffle.user.controller.RegisterRequestValidator
import ch.tkoc.shuffle.user.model.RegisterRequest
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.verify
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner
import org.springframework.validation.Errors

@RunWith(MockitoJUnitRunner::class)
class RegisterRequestValidatorTest {

    @Mock
    lateinit var errors: Errors

    val validator = RegisterRequestValidator()

    @Test
    fun usernameTooShort() {
        validator.validate(RegisterRequest(username = "abc", email = "foo@bar.com", password = "password123"), errors)
        verify(errors).rejectValue("username", "username.invalid")
    }

    @Test
    fun usernameTooLong() {
        validator.validate(RegisterRequest(username = "abcdabcdabcdabcde", email = "foo@bar.com", password = "password123"), errors)
        verify(errors).rejectValue("username", "username.invalid")
    }

    @Test
    fun usernameWrongChars() {
        validator.validate(RegisterRequest(username="abcd$", email="foo@bar.com", password = "password123"), errors)
        verify(errors).rejectValue("username", "username.invalid")
    }

    @Test
    fun validRequest() {
        validator.validate(RegisterRequest(username="better_weather06", email="foo@bar.com", password="password123"), errors)
        verify(errors, never()).rejectValue(any(), any())
    }

    @Test
    fun passwordTooShort() {
        validator.validate(RegisterRequest(username="better_weather06", email="foo@bar.com", password="passwor"), errors)
        verify(errors).rejectValue("password", "password.invalid")
    }

    @Test
    fun passwordOnlyLetters() {
        validator.validate(RegisterRequest(username="better_weather06", email="foo@bar.com", password="password"), errors)
        verify(errors).rejectValue("password", "password.invalid")
    }
}