package team.comit.simtong.domain.auth.model

import team.comit.simtong.domain.auth.exception.ExceededSendAuthCodeRequestException
import team.comit.simtong.global.DomainPropertiesKey
import team.comit.simtong.global.annotation.Aggregate
import team.comit.simtong.global.annotation.Default
import java.lang.System.getProperty

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
        @JvmField
        val MAX_ATTEMPT_COUNT: Short = getProperty(DomainPropertiesKey.AUTHCODELIMIT_MAX_ATTEMPT_COUNT).toShort()

        @JvmField
        val EXPIRED: Int = getProperty(DomainPropertiesKey.AUTHCODELIMIT_EXPIRED).toInt()

        @JvmField
        val VERIFIED_EXPIRED: Int = getProperty(DomainPropertiesKey.AUTHCODELIMIT_VERIFIED_EXPIRED).toInt()

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