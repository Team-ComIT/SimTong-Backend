package team.comit.simtong.domain.user.exception

import team.comit.simtong.domain.user.error.UserErrorCode
import team.comit.simtong.global.error.BusinessException

/**
 *
 * Different Password Error를 발생시키는 DifferentPasswordException
 *
 * @author kimbeomjin
 * @date 2022/09/08
 * @version 1.0.0
 **/
class DifferentPasswordException private constructor() : BusinessException(UserErrorCode.DIFFERENT_PASSWORD) {

    companion object {
        @JvmField
        val EXCEPTION = DifferentPasswordException()
    }

}