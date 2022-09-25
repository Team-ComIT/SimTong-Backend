package team.comit.simtong.domain.auth.exception

import team.comit.simtong.domain.auth.error.AuthErrorCode
import team.comit.simtong.global.error.BusinessException

/**
 *
 * AuthCode Not Found Error를 발생시키는 AuthCodeNotFoundException
 *
 * @author Chokyunghyeon
 * @date 2022/09/25
 * @version 1.0.0
 **/
class AuthCodeNotFoundException private constructor(): BusinessException(AuthErrorCode.AUTHCODE_NOT_FOUND) {

    companion object {
        @JvmField
        val EXCEPTION = AuthCodeNotFoundException()
    }

}