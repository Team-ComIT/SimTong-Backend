package team.comit.simtong.global.exception

import team.comit.simtong.global.error.BusinessException
import team.comit.simtong.global.error.GlobalErrorCode

/**
 *
 * 허용되지 않은 HTTP 메서드를 사용할 경우 발생시키는 MethodNotAllowedException
 *
 * @author kimbeomjin
 * @date 2022/08/22
 * @version 1.0.0
 **/
class MethodNotAllowedException private constructor() : BusinessException(GlobalErrorCode.METHOD_NOT_ALLOWED) {

    companion object {
        @JvmField
        val EXCEPTION = MethodNotAllowedException()
    }
}