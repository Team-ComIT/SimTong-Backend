package team.comit.simtong.domain.user.spi

import team.comit.simtong.domain.auth.model.AuthCodeLimit

/**
 *
 * User Domain에서 AuthCodeLimit Domain에 관한 Query를 요청하는 UserQueryAuthCodeLimitPort
 *
 * @author kimbeomjin
 * @date 2022/09/18
 * @version 1.0.0
 **/
interface UserQueryAuthCodeLimitPort {

    fun queryAuthCodeLimitByEmail(email: String): AuthCodeLimit?

}