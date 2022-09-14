package team.comit.simtong.domain.auth.spi

import team.comit.simtong.domain.auth.model.AuthCodePolicy

/**
 *
 * Domain에서 AuthCodePolicy에 관한 Query를 요청하는 DomainQueryAuthCodePolicy
 *
 * @author Chokyunghyeon
 * @date 2022/09/09
 * @version 1.0.0
 **/
interface DomainQueryAuthCodePolicyPort {

    fun queryAuthCodePolicyByEmail(email: String): AuthCodePolicy?

}