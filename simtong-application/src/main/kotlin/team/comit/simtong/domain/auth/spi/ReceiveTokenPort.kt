package team.comit.simtong.domain.auth.spi

import team.comit.simtong.domain.auth.usecase.dto.TokenResponse
import team.comit.simtong.domain.user.model.Authority
import java.util.*

/**
 *
 * Jwt 토큰을 요청하는 ReceiveTokenPort
 *
 * @author Chokyunghyeon
 * @date 2022/09/01
 * @version 1.0.0
 **/
interface ReceiveTokenPort {

    fun generateJsonWebToken(userId: UUID, authority: Authority): TokenResponse

}