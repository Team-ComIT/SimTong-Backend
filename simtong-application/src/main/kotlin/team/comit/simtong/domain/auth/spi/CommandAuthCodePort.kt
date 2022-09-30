package team.comit.simtong.domain.auth.spi

import team.comit.simtong.domain.auth.model.AuthCode

/**
 *
 * AuthCode의 저장을 요청하는 CommandAuthCodePort
 *
 * @author Chokyunghyeon
 * @date 2022/09/24
 * @version 1.0.0
 **/
interface CommandAuthCodePort {

    fun save(authCode: AuthCode): AuthCode

}