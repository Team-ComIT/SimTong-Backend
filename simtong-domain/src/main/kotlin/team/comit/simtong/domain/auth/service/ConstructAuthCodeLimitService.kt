package team.comit.simtong.domain.auth.service

import team.comit.simtong.domain.auth.model.AuthCodeLimit
import team.comit.simtong.global.annotation.DomainService

/**
 *
 * AuthCodeLimit을 생성하는 ConstructAuthCodeLimitService
 *
 * @author Chokyunghyeon
 * @date 2022/09/29
 * @version 1.0.0
 **/
@DomainService
class ConstructAuthCodeLimitService {

    fun construct(email: String): AuthCodeLimit {
        return AuthCodeLimit(
            key = email,
            expirationTime = AuthCodeLimit.EXPIRED,
            attemptCount = 0,
            isVerified = false
        )
    }

    fun verified(email: String): AuthCodeLimit {
        return AuthCodeLimit(
            key = email,
            expirationTime = AuthCodeLimit.VERIFIED_EXPIRED,
            attemptCount = AuthCodeLimit.MAX_ATTEMPT_COUNT,
            isVerified = true
        )
    }

}