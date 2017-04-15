package ch.tkoc.shuffle.user.controller

import ch.tkoc.shuffle.user.model.RegisterRequest
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator

@Component
class RegisterRequestValidator : Validator {

    override fun validate(target: Any, errors: Errors) {
        if(target is RegisterRequest) {
            if(!"[\\w]{4,16}".toRegex().matches(target.username)) {
                errors.rejectValue("username", "username.invalid")
            }
            if("(\\p{L}+|\\d+)".toRegex().matches(target.password) || !".{8,}".toRegex().matches(target.password)) {
                errors.rejectValue("password", "password.invalid")
            }
        }
    }

    override fun supports(clazz: Class<*>?): Boolean = RegisterRequest::class.java.isAssignableFrom(clazz)
}
