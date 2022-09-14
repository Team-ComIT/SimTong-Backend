package team.comit.simtong.domain.auth.exception

import team.comit.simtong.domain.auth.error.AuthErrorCode
import team.comit.simtong.global.error.BusinessException

/**
 *
 * Already Used Email Error를 발생시키는 UsedEmailException
 *
 * @author Chokyunghyeon
 * @date 2022/09/04
 * @version 1.0.0
 **/
class UsedEmailException private constructor(): BusinessException(AuthErrorCode.ALREADY_USED_EMAIL) {

    companion object {
        @JvmField
        val EXCEPTION = UsedEmailException()
    }

}