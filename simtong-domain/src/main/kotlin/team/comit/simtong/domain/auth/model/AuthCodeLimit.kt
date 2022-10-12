package team.comit.simtong.domain.auth.model

import team.comit.simtong.domain.auth.exception.ExceededSendAuthCodeRequestException
import team.comit.simtong.global.annotation.Aggregate
import team.comit.simtong.global.annotation.Default

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
data class AuthCodeLimit @Default constructor(
    val key: String,

    val expirationTime: Int,

    val attemptCount: Short,

    val isVerified: Boolean
) {

    constructor(email: String) : this(
        key = email,
        expirationTime = EXPIRED,
        attemptCount = 0,
        isVerified = false
    )

    companion object {
        // TODO 환경 변수 관리

        const val MAX_ATTEMPT_COUNT: Short = 5
        const val EXPIRED = 1800
        const val VERIFIED_EXPIRED = 2700

        fun certified(email: String) = AuthCodeLimit(
            key = email,
            expirationTime = VERIFIED_EXPIRED,
            attemptCount = MAX_ATTEMPT_COUNT,
            isVerified = true
        )
    }

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