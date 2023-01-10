package team.comit.simtong.global.security.principle

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import team.comit.simtong.domain.user.model.value.Authority
import java.util.*

/**
 *
 * 사용자의 인증 정보를 관리하는 AuthDetails
 *
 * @author kimbeomjin
 * @date 2022/08/31
 * @version 1.0.0
 **/
class AuthDetails(
    private val userId: UUID,
    private val authority: Authority
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(SimpleGrantedAuthority(authority.name))
    }

    override fun getPassword(): String? {
        return null
    }

    override fun getUsername(): String {
        return userId.toString()
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}