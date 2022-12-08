package team.comit.simtong.persistence.auth

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.comit.simtong.domain.auth.model.AuthCode
import team.comit.simtong.domain.auth.spi.AuthCodePort
import team.comit.simtong.persistence.auth.mapper.AuthCodeMapper
import team.comit.simtong.persistence.auth.repository.AuthCodeRepository

/**
 *
 * AuthCode를 관리하는 AuthCodePersistenceAdapter
 *
 * @author Chokyunghyeon
 * @date 2022/09/25
 * @version 1.0.0
 **/
@Component
class AuthCodePersistenceAdapter(
    private val authCodeMapper: AuthCodeMapper,
    private val authCodeRepository: AuthCodeRepository
): AuthCodePort {

    override fun queryAuthCodeByEmail(email: String): AuthCode? {
        return authCodeRepository.findByIdOrNull(email)
            .let(authCodeMapper::toDomain)
    }

    override fun save(authCode: AuthCode): AuthCode {
        return authCodeRepository.save(
            authCodeMapper.toEntity(authCode)
        ).let { authCodeMapper.toDomain(it)!! }
    }

}