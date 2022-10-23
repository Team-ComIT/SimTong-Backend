package team.comit.simtong.domain.auth.model

import net.bytebuddy.utility.RandomString
import team.comit.simtong.global.annotation.Aggregate
import team.comit.simtong.global.annotation.Default
import java.lang.System.getenv

/**
 *
 * AuthCodeAggregate Root를 담당하는 AuthCode
 *
 * @author Chokyunghyeon
 * @date 2022/09/24
 * @version 1.0.0
 **/
@Aggregate
data class AuthCode @Default constructor(
    val key: String,

    val code: String,

    val expirationTime: Int
) {

    constructor(email: String) : this(
        key = email,
        code = RandomString(6).nextString(),
        expirationTime = EXPIRED
    )

    companion object {
        @JvmField
        val EXPIRED: Int = getenv("AUTHCODE_EXPIRED").toInt()
    }

}