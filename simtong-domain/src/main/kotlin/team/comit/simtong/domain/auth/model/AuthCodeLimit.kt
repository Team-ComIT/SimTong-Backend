package team.comit.simtong.domain.auth.model

import team.comit.simtong.domain.auth.exception.ExceededSendAuthCodeRequestException
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
        // TODO 환경 변수 관리

        const val EXPIRED = 1800
        const val MAX_ATTEMPT_COUNT: Short = 5
        const val VERIFIED_EXPIRED = 2700
    }

    constructor(email: String) : this(
        key = email,
        expirationTime = EXPIRED,
        attemptCount = 0,
        isVerified = false
    )

    fun increaseCount(): AuthCodeLimit {
        if (attemptCount >= MAX_ATTEMPT_COUNT) {
            throw ExceededSendAuthCodeRequestException.EXCEPTION
        }

        return AuthCodeLimit(
            key = key,
            expirationTime = expirationTime,
            attemptCount = attemptCount.inc(),
            isVerified = false
        )
    }

}