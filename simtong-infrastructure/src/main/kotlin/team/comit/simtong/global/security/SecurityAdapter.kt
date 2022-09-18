package team.comit.simtong.global.security

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import team.comit.simtong.domain.user.spi.UserSecurityPort

/**
 *
 * Security 관련 기능을 관리하는 SecurityAdapter
 *
 * @author Chokyunghyeon
 * @date 2022/09/05
 * @version 1.0.0
 **/
@Component
class SecurityAdapter(
    private val passwordEncoder: PasswordEncoder
) : UserSecurityPort {
    override fun compare(target: String, encryptedPassword: String): Boolean {
        return passwordEncoder.matches(target, encryptedPassword)
    }

    override fun encode(password: String): String {
        return passwordEncoder.encode(password)
    }

}