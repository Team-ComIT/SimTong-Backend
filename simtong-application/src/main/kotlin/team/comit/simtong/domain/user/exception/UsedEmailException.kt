package team.comit.simtong.domain.user.exception

import team.comit.simtong.domain.user.error.UserErrorCode
import team.comit.simtong.global.error.BusinessException

/**
 *
 * Already Email Used Error를 발생시키는 UsedEmailException
 *
 * @author JoKyungHyeon
 * @date 2022/09/04
 * @version 1.0.0
 **/
class UsedEmailException private constructor(): BusinessException(UserErrorCode.ALREADY_USED_EMAIL) {

    companion object {
        @JvmField
        val EXCEPTION = UsedEmailException()
    }

}