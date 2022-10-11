package team.comit.simtong.domain.user.exception

import team.comit.simtong.domain.user.error.UserErrorCode
import team.comit.simtong.global.error.BusinessException

class NotAdminAccountException private constructor() : BusinessException(UserErrorCode.NOT_ADMIN_ACCOUNT) {

    companion object {
        @JvmField
        val EXCEPTION = NotAdminAccountException()
    }

}