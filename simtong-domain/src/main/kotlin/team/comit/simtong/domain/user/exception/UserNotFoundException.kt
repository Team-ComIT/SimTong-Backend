package team.comit.simtong.domain.user.exception

import team.comit.simtong.domain.user.error.UserErrorCode
import team.comit.simtong.global.error.BusinessException

/**
 *
 * User Not Found Error를 발생시키는 UserNotFoundException
 *
 * @author Chokyunghyeon
 * @date 2022/09/04
 * @version 1.0.0
 **/
class UserNotFoundException private constructor(): BusinessException(UserErrorCode.USER_NOT_FOUND) {

    companion object {
        @JvmField
        val EXCEPTION = UserNotFoundException()
    }

}