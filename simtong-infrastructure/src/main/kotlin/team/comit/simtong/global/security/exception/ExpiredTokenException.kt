package team.comit.simtong.global.security.exception

import team.comit.simtong.global.error.BusinessException
import team.comit.simtong.global.security.error.SecurityErrorCode

/**
 *
 * Expired Token Error를 발생시키는 ExpiredTokenException
 *
 * @author Chokyunghyeon
 * @date 2022/09/02
 * @version 1.0.0
 **/
class ExpiredTokenException private constructor() : BusinessException(SecurityErrorCode.EXPIRED_TOKEN) {

    companion object {
        @JvmField
        val EXCEPTION = ExpiredTokenException()
    }
}