package team.comit.simtong.domain.auth.model

import team.comit.simtong.domain.auth.exception.AuthExceptions
import team.comit.simtong.global.DomainProperties.getProperty
import team.comit.simtong.global.DomainPropertiesPrefix
import team.comit.simtong.global.annotation.Aggregate

/**
 *
 * AuthCodeLimitAggregate Root를 담당하는 AuthCodeLimit
 *
 * @author Chokyunghyeon
 * @author kimbeomjin
 * @date 2022/09/11
 * @version 1.2.5
 **/
@Aggregate
data class AuthCodeLimit(
    val key: String,

    val expirationTime: Int,

    val attemptCount: Short,

    val verified: Boolean
) {
    companion object {
        @JvmField
        val MAX_ATTEMPT_COUNT: Short = getProperty(DomainPropertiesPrefix.AUTHCODELIMIT_MAX_ATTEMPT_COUNT).toShort()

        @JvmField
        val EXPIRED: Int = getProperty(DomainPropertiesPrefix.AUTHCODELIMIT_EXP).toInt()

        @JvmField
        val VERIFIED_EXPIRED: Int = getProperty(DomainPropertiesPrefix.AUTHCODELIMIT_VERIFIED_EXP).toInt()

        fun issue(email: String) = AuthCodeLimit(
            key = email,
            expirationTime = EXPIRED,
            attemptCount = 0,
            verified = false
        )

        fun certified(email: String) = AuthCodeLimit(
            key = email,
            expirationTime = VERIFIED_EXPIRED,
            attemptCount = MAX_ATTEMPT_COUNT,
            verified = true
        )
    }

    fun increaseCount(): AuthCodeLimit {
        checkNotYetVerified()
        checkNotExceededAttemptCount()

        return AuthCodeLimit(
            key = key,
            expirationTime = expirationTime,
            attemptCount = attemptCount.inc(),
            verified = false
        )
    }

    private fun checkNotYetVerified() {
        if (verified) {
            throw AuthExceptions.AlreadyCertifiedEmail()
        }
    }

    private fun checkNotExceededAttemptCount() {
        if (attemptCount >= MAX_ATTEMPT_COUNT) {
            throw AuthExceptions.ExceededSendAuthCodeRequest()
        }
    }
}