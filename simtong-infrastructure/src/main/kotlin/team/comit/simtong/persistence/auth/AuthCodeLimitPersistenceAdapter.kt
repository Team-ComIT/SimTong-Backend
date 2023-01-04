package team.comit.simtong.persistence.auth

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.comit.simtong.domain.auth.model.AuthCodeLimit
import team.comit.simtong.domain.auth.spi.AuthCodeLimitPort
import team.comit.simtong.persistence.auth.mapper.AuthCodeLimitMapper
import team.comit.simtong.persistence.auth.repository.AuthCodeLimitRepository

/**
 *
 * AuthCodeLimit을 관리하는 AuthCodeLimitAdapter
 *
 * @author Chokyunghyeon
 * @author kimbeomjin
 * @date 2022/09/09
 * @version 1.2.3
 **/
@Component
class AuthCodeLimitPersistenceAdapter(
    private val authCodeLimitMapper: AuthCodeLimitMapper,
    private val authCodeLimitRepository: AuthCodeLimitRepository
) : AuthCodeLimitPort {

    override fun queryAuthCodeLimitByEmail(email: String): AuthCodeLimit? {
        return authCodeLimitRepository.findByIdOrNull(email)
            .let(authCodeLimitMapper::toDomain)
    }

    override fun save(authCodeLimit: AuthCodeLimit): AuthCodeLimit {
        return authCodeLimitRepository.save(
            authCodeLimitMapper.toEntity(authCodeLimit)
        ).let(authCodeLimitMapper::toDomain)!!
    }

    override fun delete(authCodeLimit: AuthCodeLimit) {
        authCodeLimitRepository.delete(
            authCodeLimitMapper.toEntity(authCodeLimit)
        )
    }

}