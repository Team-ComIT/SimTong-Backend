package team.comit.simtong.persistence.auth

import org.springframework.stereotype.Component
import team.comit.simtong.domain.auth.spi.QueryRefreshTokenPort
import team.comit.simtong.persistence.auth.mapper.RefreshTokenMapper
import team.comit.simtong.persistence.auth.repository.RefreshTokenRepository

/**
 *
 * Refresh Token을 관리하는 RefreshTokenAdapter
 *
 * @author Chokyunghyeon
 * @date 2022/09/18
 * @version 1.0.0
 **/
@Component
class RefreshTokenAdapter(
    private val refreshTokenRepository: RefreshTokenRepository,
    private val refreshTokenMapper: RefreshTokenMapper
): QueryRefreshTokenPort {

    override fun queryRefreshTokenByToken(token: String) = refreshTokenMapper.toDomain(
        refreshTokenRepository.queryRefreshTokenEntityByToken(token)
    )

}