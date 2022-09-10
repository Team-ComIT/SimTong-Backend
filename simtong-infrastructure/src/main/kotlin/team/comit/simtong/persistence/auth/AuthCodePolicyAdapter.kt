package team.comit.simtong.persistence.auth

import org.springframework.stereotype.Component
import team.comit.simtong.domain.auth.exception.UncertifiedEmailException
import team.comit.simtong.domain.auth.spi.CheckAuthCodePolicyPort
import team.comit.simtong.persistence.auth.repository.AuthCodePolicyRepository

/**
 *
 * AuthCodePolicy를 관리하는 AuthCodePolicyAdapter
 *
 * @author Chokyunghyeon
 * @date 2022/09/09
 * @version 1.0.0
 **/
@Component
class AuthCodePolicyAdapter(
    private val authCodePolicyRepository: AuthCodePolicyRepository
): CheckAuthCodePolicyPort {

    override fun checkCertifiedEmail(email: String): Boolean {
        val authCodePolicy = authCodePolicyRepository.queryAuthCodePolicyEntityByKey(email) ?: throw UncertifiedEmailException.EXCEPTION

        return authCodePolicy.isVerified
    }
}