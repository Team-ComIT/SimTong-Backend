package team.comit.simtong.domain.auth.model

import net.bytebuddy.utility.RandomString
import team.comit.simtong.global.annotation.Aggregate

/**
 *
 * AuthCodeAggregate Root를 담당하는 AuthCode
 *
 * @author Chokyunghyeon
 * @date 2022/09/24
 * @version 1.0.0
 **/
@Aggregate
class AuthCode(
    val key: String,

    val code: String,

    val expirationTime: Int
) {

    companion object {
        const val EXPIRED = 180

        fun default(email: String) = AuthCode(
            key = email,
            code = RandomString(6).nextString(),
            expirationTime = EXPIRED
        )
    }

}