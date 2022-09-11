package team.comit.simtong.persistence.auth

import org.springframework.stereotype.Component
import team.comit.simtong.domain.auth.exception.UncertifiedEmailException
import team.comit.simtong.domain.auth.model.AuthCodePolicy
import team.comit.simtong.domain.auth.spi.DomainQueryAuthCodePolicyPort
import team.comit.simtong.persistence.auth.mapper.AuthCodePolicyMapper
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
    private val authCodePolicyMapper: AuthCodePolicyMapper,
    private val authCodePolicyRepository: AuthCodePolicyRepository
): DomainQueryAuthCodePolicyPort {

    override fun queryAuthCodePolicyByEmail(email: String): AuthCodePolicy {
        return authCodePolicyMapper.toDomain(
            authCodePolicyRepository.queryAuthCodePolicyEntityByKey(email) ?: throw UncertifiedEmailException.EXCEPTION
        )

    }
}