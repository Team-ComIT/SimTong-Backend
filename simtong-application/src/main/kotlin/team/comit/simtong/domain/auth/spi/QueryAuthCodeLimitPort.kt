package team.comit.simtong.domain.auth.spi

import team.comit.simtong.domain.auth.model.AuthCodeLimit

/**
 *
 * AuthCodeLimit Domain에 관한 Query를 요청하는 QueryAuthCodeLimitPort
 *
 * @author Chokyunghyeon
 * @date 2022/09/24
 * @version 1.0.0
 **/
interface QueryAuthCodeLimitPort {

    fun queryAuthCodeLimitByEmail(email: String): AuthCodeLimit?

}