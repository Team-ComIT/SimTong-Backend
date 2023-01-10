package team.comit.simtong.domain.auth.model

import team.comit.simtong.domain.auth.model.value.Code
import team.comit.simtong.global.DomainProperties.getProperty
import team.comit.simtong.global.DomainPropertiesPrefix
import team.comit.simtong.global.annotation.Aggregate

/**
 *
 * AuthCode Aggregate Root를 담당하는 AuthCode
 *
 * @author Chokyunghyeon
 * @author kimbeomjin
 * @date 2022/09/24
 * @version 1.2.5
 **/
@Aggregate
data class AuthCode(
    val key: String,

    val code: Code,

    val expirationTime: Int
) {

    companion object {
        @JvmField
        val EXPIRED = getProperty(DomainPropertiesPrefix.AUTHCODE_EXP).toInt()

        fun of(key: String, code: Code, expirationTime: Int) = AuthCode(
            key = key,
            code = code,
            expirationTime = expirationTime
        )

        fun issue(email: String) = AuthCode(
            key = email,
            code = Code.defaultValue(),
            expirationTime = EXPIRED
        )
    }
}
