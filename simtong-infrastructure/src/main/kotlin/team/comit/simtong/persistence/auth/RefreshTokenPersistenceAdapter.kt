package team.comit.simtong.persistence.auth

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.comit.simtong.domain.auth.spi.RefreshTokenPort
import team.comit.simtong.persistence.auth.mapper.RefreshTokenMapper
import team.comit.simtong.persistence.auth.repository.RefreshTokenRepository

/**
 *
 * Refresh Token을 관리하는 RefreshTokenAdapter
 *
 * @author Chokyunghyeon
 * @author kimbeomjin
 * @date 2022/09/18
 * @version 1.0.0
 **/
@Component
class RefreshTokenPersistenceAdapter(
    private val refreshTokenRepository: RefreshTokenRepository,
    private val refreshTokenMapper: RefreshTokenMapper
) : RefreshTokenPort {

    override fun queryRefreshTokenByToken(token: String) = refreshTokenMapper.toDomain(
        refreshTokenRepository.findByIdOrNull(token)
    )

}