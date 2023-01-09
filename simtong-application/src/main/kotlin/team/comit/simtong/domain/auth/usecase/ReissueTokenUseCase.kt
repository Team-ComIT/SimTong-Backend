package team.comit.simtong.domain.auth.usecase

import team.comit.simtong.domain.auth.dto.TokenResponse
import team.comit.simtong.domain.auth.exception.AuthExceptions
import team.comit.simtong.domain.auth.spi.JwtPort
import team.comit.simtong.domain.auth.spi.QueryRefreshTokenPort
import team.comit.simtong.global.annotation.UseCase

/**
 *
 * 토큰 재발급을 담당하는 ReissueTokenUseCase
 *
 * @author Chokyunghyeon
 * @author kimbeomjin
 * @date 2022/09/18
 * @version 1.2.5
 **/
@UseCase
class ReissueTokenUseCase(
    private val jwtPort: JwtPort,
    private val queryRefreshTokenPort: QueryRefreshTokenPort
) {

    fun execute(token: String): TokenResponse {
        val refreshToken = queryRefreshTokenPort.queryRefreshTokenByToken(token)
            ?: throw AuthExceptions.RefreshTokenNotFound()

        return jwtPort.receiveToken(
            userId = refreshToken.userId,
            authority = refreshToken.authority
        )
    }
}