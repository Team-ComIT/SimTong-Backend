package team.comit.simtong.domain.user.exception

import team.comit.simtong.domain.user.error.UserErrorCode
import team.comit.simtong.global.error.BusinessException

/**
 *
 * Not Enough Permission Error를 발생시키는 NotEnoughPermissionException
 *
 * @author Chokyunghyeon
 * @date 2022/11/21
 * @version 1.0.0
 **/
class NotEnoughPermissionException private constructor() : BusinessException(UserErrorCode.NOT_ENOUGH_PERMISSION) {

    companion object {
        @JvmField
        val EXCEPTION = NotEnoughPermissionException()
    }

}