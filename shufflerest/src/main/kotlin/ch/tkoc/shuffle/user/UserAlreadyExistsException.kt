package ch.tkoc.shuffle.user

import ch.tkoc.shuffle.user.model.RegisterRequest

class UserAlreadyExistsException(val registerRequest: RegisterRequest): Exception()
