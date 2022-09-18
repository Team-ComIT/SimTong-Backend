package team.comit.simtong.domain.auth.usecase

import team.comit.simtong.domain.auth.exception.RefreshTokenNotFoundException
import team.comit.simtong.domain.auth.spi.QueryRefreshTokenPort
import team.comit.simtong.domain.auth.spi.ReceiveTokenPort
import team.comit.simtong.domain.auth.usecase.dto.TokenResponse
import team.comit.simtong.global.annotation.UseCase

/**
 *
 * 토큰 재발급을 담당하는 ReissueTokenUseCase
 *
 * @author Chokyunghyeon
 * @date 2022/09/18
 * @version 1.0.0
 **/
@UseCase
class ReissueTokenUseCase(
    private val receiveTokenPort: ReceiveTokenPort,
    private val queryRefreshTokenPort: QueryRefreshTokenPort
) {

    fun execute(request: String): TokenResponse {
        val token = queryRefreshTokenPort.queryRefreshTokenByToken(request)
            ?: throw RefreshTokenNotFoundException.EXCEPTION

        return receiveTokenPort.generateJsonWebToken(
            userId = token.userId,
            authority = token.authority
        )
    }
}