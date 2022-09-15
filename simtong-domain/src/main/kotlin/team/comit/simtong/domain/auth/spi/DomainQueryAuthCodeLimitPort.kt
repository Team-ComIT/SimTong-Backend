package team.comit.simtong.domain.auth.spi

import team.comit.simtong.domain.auth.model.AuthCodeLimit

/**
 *
 * Domain에서 AuthCodeLimit에 관한 Query를 요청하는 DomainQueryAuthCodeLimit
 *
 * @author Chokyunghyeon
 * @author kimbeomjin
 * @date 2022/09/09
 * @version 1.0.0
 **/
interface DomainQueryAuthCodeLimitPort {

    fun queryAuthCodeLimitByEmail(email: String): AuthCodeLimit?

}