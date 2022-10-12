package team.comit.simtong.domain.user.exception

import team.comit.simtong.domain.user.error.UserErrorCode
import team.comit.simtong.global.error.BusinessException

class DifferentPermissionAccountException private constructor() : BusinessException(UserErrorCode.DIFFERENT_PERMISSION_ACCOUNT) {

    companion object {
        @JvmField
        val EXCEPTION = DifferentPermissionAccountException()
    }

}