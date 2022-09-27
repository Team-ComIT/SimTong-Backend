package team.comit.simtong.domain.auth.model

import team.comit.simtong.global.annotation.Aggregate

/**
 *
 * AuthCodeLimitAggregate Root를 담당하는 AuthCodeLimit
 *
 * @author Chokyunghyeon
 * @author kimbeomjin
 * @date 2022/09/11
 * @version 1.0.0
 **/
@Aggregate
class AuthCodeLimit(
    val key: String,

    val expirationTime: Int,

    val attemptCount: Short,

    val isVerified: Boolean
) {

    companion object {
        val EXPIRED = System.getenv("AUTHCODELIMIT_EXPIRED").toInt()
        val MAX_ATTEMPT_COUNT = System.getenv("AUTHCODELIMIT_MAX_ATTEMPT_COUNT").toShort()
        val VERIFIED_EXPIRED = System.getenv("AUTHCODELIMIT_VERIFIED_EXPIRED").toInt()
    }

    fun sendAuthCode(): AuthCodeLimit {
        return AuthCodeLimit(
            key = key,
            expirationTime = expirationTime,
            attemptCount = attemptCount.inc(),
            isVerified = false
        )
    }

}