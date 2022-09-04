package team.comit.simtong.domain.auth.spi

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

    fun generateAccessToken(userId: UUID, authority: Authority): String

    fun generateRefreshToken(userId: UUID, authority: Authority): String
}