package team.comit.simtong.domain.auth.exception

import team.comit.simtong.domain.auth.error.AuthErrorCode
import team.comit.simtong.global.error.BusinessException

/**
 *
 * Uncertified Email Error를 발생시키는 UncertifiedEmailException
 *
 * @author Chokyunghyeon
 * @date 2022/09/09
 * @version 1.0.0
 **/
class UncertifiedEmailException private constructor(): BusinessException(AuthErrorCode.UNCERTIFIED_EMAIL) {

    companion object {
        @JvmField
        val EXCEPTION = UncertifiedEmailException()
    }

}