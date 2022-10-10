package team.comit.simtong.domain.auth.exception

import team.comit.simtong.domain.auth.error.AuthErrorCode
import team.comit.simtong.global.error.BusinessException

/**
 *
 * Required New Email Authentication Error를 발생시키는 RequiredNewEmailAuthenticationException
 *
 * @author Chokyunghyeon
 * @date 2022/10/10
 * @version 1.0.0
 **/
class RequiredNewEmailAuthenticationException private constructor() : BusinessException(AuthErrorCode.REQUIRED_NEW_EMAIL_AUTHENTICATION) {

    companion object {
        @JvmField
        val EXCEPTION = RequiredNewEmailAuthenticationException()
    }

}