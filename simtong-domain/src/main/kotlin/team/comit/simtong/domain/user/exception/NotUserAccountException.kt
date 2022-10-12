package team.comit.simtong.domain.user.exception

import team.comit.simtong.domain.user.error.UserErrorCode
import team.comit.simtong.global.error.BusinessException

class NotUserAccountException private constructor() : BusinessException(UserErrorCode.NOT_USER_ACCOUNT) {

    companion object {
        @JvmField
        val EXCEPTION = NotUserAccountException()
    }

}