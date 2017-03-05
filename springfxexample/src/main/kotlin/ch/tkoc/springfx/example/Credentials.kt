package ch.tkoc.springfx.example


class Credentials(val username: String, val password: String) {

    //TODO base64 hash

    operator fun component1() = username

    operator fun component2() = password
}