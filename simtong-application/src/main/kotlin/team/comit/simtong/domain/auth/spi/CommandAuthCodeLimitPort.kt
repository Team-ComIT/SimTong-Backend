package team.comit.simtong.domain.auth.spi

import team.comit.simtong.domain.auth.model.AuthCodeLimit

/**
 *
 * AuthCodeLimit Domain에 관한 명령을 하는 CommandAuthCodeLimitPort
 *
 * @author Chokyunghyeon
 * @date 2022/09/24
 * @version 1.0.0
 **/
interface CommandAuthCodeLimitPort {

    fun save(authCodeLimit: AuthCodeLimit): AuthCodeLimit

}