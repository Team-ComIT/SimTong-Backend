package team.comit.simtong.domain.auth.spi

import team.comit.simtong.domain.auth.model.AuthCode

/**
 *
 * AuthCode Domain에 관한 Query를 요청하는 QueryAuthCodePort
 *
 * @author Chokyunghyeon
 * @date 2022/09/25
 * @version 1.0.0
 **/
interface QueryAuthCodePort {

    fun queryAuthCodeByEmail(email: String): AuthCode?

}