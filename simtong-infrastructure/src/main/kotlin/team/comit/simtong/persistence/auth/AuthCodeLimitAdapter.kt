package team.comit.simtong.persistence.auth

import org.springframework.stereotype.Component
import team.comit.simtong.domain.auth.model.AuthCodeLimit
import team.comit.simtong.domain.auth.spi.DomainQueryAuthCodeLimitPort
import team.comit.simtong.persistence.auth.mapper.AuthCodeLimitMapper
import team.comit.simtong.persistence.auth.repository.AuthCodeLimitRepository

/**
 *
 * AuthCodeLimit을 관리하는 AuthCodeLimitAdapter
 *
 * @author Chokyunghyeon
 * @author kimbeomjin
 * @date 2022/09/09
 * @version 1.0.0
 **/
@Component
class AuthCodeLimitAdapter(
    private val authCodeLimitMapper: AuthCodeLimitMapper,
    private val authCodeLimitRepository: AuthCodeLimitRepository
) : DomainQueryAuthCodeLimitPort {

    override fun queryAuthCodeLimitByEmail(email: String): AuthCodeLimit? = authCodeLimitMapper.toDomain(
        authCodeLimitRepository.queryAuthCodeLimitEntityByKey(email)
    )
}