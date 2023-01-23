package team.comit.simtong.domain.auth.model

import net.bytebuddy.utility.RandomString
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
            code = Code.generate(),
            expirationTime = EXPIRED
        )
    }
}

/**
 *
 * AuthCode Aggregate 중 인증코드를 담당하는 Code
 *
 * @author kimbeomjin
 * @date 2023/01/09
 * @version 1.2.5
 **/
@JvmInline
value class Code private constructor(
    val value: String
) {

    fun match(code: String): Boolean {
        return this.value == code
    }

    companion object {
        fun of(value: String) = Code(value)

        fun generate() = Code(RandomString(6).nextString())
    }
}