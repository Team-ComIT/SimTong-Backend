package team.comit.simtong.domain.user.spi

import team.comit.simtong.domain.auth.model.AuthCodeLimit

/**
 *
 * User Domain에서 AuthCodeLimit Domain에 관한 Command를 요청하는UserCommandAuthCodeLimitPort
 *
 * @author kimbeomjin
 * @date 2022/12/06
 * @version 1.0.0
 **/
interface UserCommandAuthCodeLimitPort {

    fun delete(authCodeLimit: AuthCodeLimit)

}