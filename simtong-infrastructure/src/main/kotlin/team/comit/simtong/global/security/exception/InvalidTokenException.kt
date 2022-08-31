package team.comit.simtong.global.security.exception

import team.comit.simtong.global.error.BusinessException
import team.comit.simtong.global.security.error.SecurityErrorCode

/**
 *
 * Invalid Token Error를 발생시키는 InvalidTokenException
 *
 * @author kimbeomjin
 * @date 2022/08/31
 * @version 1.0.0
 **/
class InvalidTokenException private constructor() : BusinessException(SecurityErrorCode.INVALID_TOKEN) {

    companion object {
        @JvmField
        val EXCEPTION = InvalidTokenException()
    }
}