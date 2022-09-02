package team.comit.simtong.domain.auth.spi

import team.comit.simtong.domain.user.model.Authority

/**
  *
  * Jwt 토큰을 요청하는 ReceiveTokenPort
  *
  * @author JoKyungHyeon
  * @date 2022/09/01
  * @version 1.0.0
 **/
interface ReceiveTokenPort {

    fun generateAccessToken(email: String, authority: Authority): String

    fun generateRefreshToken(email: String, authority: Authority): String
}