package team.comit.simtong.global.security

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import team.comit.simtong.domain.auth.spi.SecurityPort
import java.util.*

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
) : SecurityPort {
    override fun compare(target: String, encryptedPassword: String): Boolean {
        return passwordEncoder.matches(target, encryptedPassword)
    }

    override fun encode(password: String): String {
        return passwordEncoder.encode(password)
    }

    override fun getCurrentUserId(): UUID {
        return UUID.fromString(SecurityContextHolder.getContext().authentication.name)
    }

}