package team.comit.simtong.domain.auth.policy

import net.bytebuddy.utility.RandomString
import team.comit.simtong.domain.auth.exception.CertifiedEmailException
import team.comit.simtong.domain.auth.exception.ExceededSendAuthCodeRequestException
import team.comit.simtong.domain.auth.model.AuthCode
import team.comit.simtong.domain.auth.model.AuthCodeLimit
import team.comit.simtong.domain.auth.spi.QueryAuthCodeLimitPort
import team.comit.simtong.global.annotation.Policy

/**
 *
 * 이메일 인증 정책을 관리하는 SendAuthCodeLimitPolicy
 *
 * @author Chokyunghyeon
 * @date 2022/09/24
 * @version 1.0.0
 **/
@Policy
class SendAuthCodePolicy(
    private val queryAuthCodeLimitPort: QueryAuthCodeLimitPort
) {

    fun restriction(email: String): AuthCodeLimit {
        val authCodeLimit = queryAuthCodeLimitPort.queryAuthCodeLimitByEmail(email)
            ?: AuthCodeLimit(
                key = email,
                expirationTime = AuthCodeLimit.EXPIRED,
                attemptCount = 0,
                isVerified = false
            )

        if(authCodeLimit.isVerified) {
            throw CertifiedEmailException.EXCEPTION
        }

        if (authCodeLimit.attemptCount >= AuthCodeLimit.MAX_ATTEMPT_COUNT) {
            throw ExceededSendAuthCodeRequestException.EXCEPTION
        }

        return authCodeLimit.sendAuthCode()
    }

    fun implement(email: String): AuthCode {
        return AuthCode(
            key = email,
            code = RandomString(6).nextString(),
            expirationTime = AuthCode.EXPIRED
        )
    }

}