package team.comit.simtong.domain.auth.exception

import team.comit.simtong.domain.auth.error.AuthErrorCode
import team.comit.simtong.global.error.BusinessException

/**
 *
 * Already Certified Email Error를 발생시키는 CertifiedEmailException
 *
 * @author Chokyunghyeon
 * @date 2022/09/24
 * @version 1.0.0
 **/
class CertifiedEmailException private constructor(): BusinessException(AuthErrorCode.ALREADY_CERTIFIED_EMAIL) {

    companion object {
        @JvmField
        val EXCEPTION = CertifiedEmailException()
    }

}