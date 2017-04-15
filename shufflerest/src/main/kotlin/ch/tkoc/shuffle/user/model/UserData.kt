package ch.tkoc.shuffle.user.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class UserData(private val username: String,
                    private val password: String,
                    var email: String,
                    private var enabled: Boolean = true,
                    private var credentialsNonExpired: Boolean = true,
                    private var accountNonExpired: Boolean = true,
                    private var accountNonLocked: Boolean = true,
                    private var authorities: Collection<GrantedAuthority> = listOf()) : UserDetails {

    override fun getUsername() = username

    override fun getPassword() = password

    override fun isEnabled() = enabled

    override fun isCredentialsNonExpired() = credentialsNonExpired

    override fun isAccountNonExpired() = accountNonExpired

    override fun isAccountNonLocked() = accountNonLocked

    override fun getAuthorities() = authorities
}