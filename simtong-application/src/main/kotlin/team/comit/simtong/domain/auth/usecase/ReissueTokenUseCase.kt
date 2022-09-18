package team.comit.simtong.domain.auth.usecase

import team.comit.simtong.domain.auth.exception.RefreshTokenNotFoundException
import team.comit.simtong.domain.auth.spi.JwtPort
import team.comit.simtong.domain.auth.spi.QueryRefreshTokenPort
import team.comit.simtong.domain.auth.usecase.dto.TokenResponse
import team.comit.simtong.global.annotation.UseCase

/**
 *
 * 토큰 재발급을 담당하는 ReissueTokenUseCase
 *
 * @author Chokyunghyeon
 * @author kimbeomjin
 * @date 2022/09/18
 * @version 1.0.0
 **/
@UseCase
class ReissueTokenUseCase(
    private val jwtPort: JwtPort,
    private val queryRefreshTokenPort: QueryRefreshTokenPort
) {

    fun execute(request: String): TokenResponse {
        val token = queryRefreshTokenPort.queryRefreshTokenByToken(request)
            ?: throw RefreshTokenNotFoundException.EXCEPTION

        return jwtPort.receiveToken(
            userId = token.userId,
            authority = token.authority
        )
    }
}