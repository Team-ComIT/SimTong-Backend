package team.comit.simtong.domain.auth.exception

import team.comit.simtong.domain.auth.error.AuthErrorCode
import team.comit.simtong.global.error.BusinessException

/**
 *
 * Refresh Not Found Error를 발생시키는 RefreshTokenNotFoundException
 *
 * @author Chokyunghyeon
 * @date 2022/09/18
 * @version 1.0.0
 **/
class RefreshTokenNotFoundException private constructor(): BusinessException(AuthErrorCode.REFRESH_TOKEN_NOT_FOUND) {

    companion object {
        @JvmField
        val EXCEPTION = RefreshTokenNotFoundException()
    }

}