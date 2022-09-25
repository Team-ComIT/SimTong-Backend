package team.comit.simtong.domain.auth.exception

import team.comit.simtong.domain.auth.error.AuthErrorCode
import team.comit.simtong.global.error.BusinessException

/**
 *
 * Exceeded Send AuthCode Request Error를 발생시키는 ExceededSendAuthCodeRequestException
 *
 * @author Chokyunghyeon
 * @date 2022/09/24
 * @version 1.0.0
 **/
class ExceededSendAuthCodeRequestException private constructor(): BusinessException(AuthErrorCode.EXCEEDED_SEND_AUTHCODE_REQUEST) {

    companion object {
        @JvmField
        val EXCEPTION = ExceededSendAuthCodeRequestException()
    }

}