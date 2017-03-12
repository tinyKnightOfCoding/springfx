package ch.tkoc.shuffle.user

class UserAlreadyExistsException(val registerRequest: RegisterRequest): Exception()
