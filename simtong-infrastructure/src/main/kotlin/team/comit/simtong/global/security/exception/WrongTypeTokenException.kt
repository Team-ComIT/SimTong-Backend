package team.comit.simtong.global.security.exception

import team.comit.simtong.global.error.BusinessException
import team.comit.simtong.global.security.error.SecurityErrorCode

/**
 *
 * 원하는 토큰 Type이 아닐 경우
 * Wrong Type Token Error를 발생시키는 TokenTypeException
 *
 * @author Chokyunghyeon
 * @date 2022/09/02
 * @version 1.0.0
 **/
class WrongTypeTokenException private constructor() : BusinessException(SecurityErrorCode.WRONG_TYPE_TOKEN) {

    companion object {
        @JvmField
        val EXCEPTION = WrongTypeTokenException()
    }
}