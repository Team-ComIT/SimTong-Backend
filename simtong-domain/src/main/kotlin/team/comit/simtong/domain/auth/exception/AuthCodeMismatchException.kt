package team.comit.simtong.domain.auth.exception

import team.comit.simtong.domain.auth.error.AuthErrorCode
import team.comit.simtong.global.error.BusinessException

/**
 *
 * AuthCode Mismatch Error를 발생시키는 AuthCodeMismatchException
 *
 * @author Chokyunghyeon
 * @date 2022/09/25
 * @version 1.0.0
 **/
class AuthCodeMismatchException private constructor(): BusinessException(AuthErrorCode.AUTHCODE_MISMATCH) {

    companion object {
        @JvmField
        val EXCEPTION = AuthCodeMismatchException()
    }

}