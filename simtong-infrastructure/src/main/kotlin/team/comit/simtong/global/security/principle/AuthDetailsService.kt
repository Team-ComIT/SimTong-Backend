package team.comit.simtong.global.security.principle

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import team.comit.simtong.global.security.exception.InvalidTokenException
import team.comit.simtong.persistence.user.repository.UserJpaRepository
import java.util.*

/**
 *
 * 사용자의 인증 정보를 불러오는 AuthDetailsService
 *
 * @author kimbeomjin
 * @date 2022/08/31
 * @version 1.0.0
 **/
@Component
class AuthDetailsService(
    private val userRepository: UserJpaRepository
) : UserDetailsService {

    override fun loadUserByUsername(userId: String): UserDetails {
        val id = UUID.fromString(userId)
        val user = userRepository.queryUserJpaEntityById(id) ?: throw InvalidTokenException.EXCEPTION

        return AuthDetails(id, user.authority)
    }
}