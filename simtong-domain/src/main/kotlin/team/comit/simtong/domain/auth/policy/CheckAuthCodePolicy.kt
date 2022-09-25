package team.comit.simtong.domain.auth.policy

import team.comit.simtong.domain.auth.exception.AuthCodeNotFoundException
import team.comit.simtong.domain.auth.model.AuthCodeLimit
import team.comit.simtong.domain.auth.spi.QueryAuthCodePort
import team.comit.simtong.global.annotation.Policy

/**
 *
 * 이메일 인증 코드 확인 정책을 관리하는 CheckAuthCodePolicy
 *
 * @author Chokyunghyeon
 * @date 2022/09/25
 * @version 1.0.0
 **/
@Policy
class CheckAuthCodePolicy(
    private val queryAuthCodePort: QueryAuthCodePort
) {

    fun implement(email: String, code: String): AuthCodeLimit {
        if (!queryAuthCodePort.existsAuthCodeByEmailAndCode(email, code)) {
            throw AuthCodeNotFoundException.EXCEPTION
        }

        return AuthCodeLimit(
            key = email,
            expirationTime = AuthCodeLimit.VERIFIED_EXPIRED,
            attemptCount = 0,
            isVerified = true
        )
    }

}