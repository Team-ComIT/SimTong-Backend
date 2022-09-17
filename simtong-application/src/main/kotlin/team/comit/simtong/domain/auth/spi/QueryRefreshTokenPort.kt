package team.comit.simtong.domain.auth.spi

import team.comit.simtong.domain.auth.model.RefreshToken

/**
 *
 * RefreshToken에 관한 Query를 요청하는 QueryRefreshTokenPort
 *
 * @author Chokyunghyeon
 * @date 2022/09/18
 * @version 1.0.0
 **/
interface QueryRefreshTokenPort {

    fun queryRefreshTokenByToken(token: String): RefreshToken?

}