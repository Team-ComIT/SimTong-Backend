package team.comit.simtong.domain.auth.exception

import team.comit.simtong.domain.auth.error.AuthErrorCode
import team.comit.simtong.global.error.BusinessException

class UsedNicknameException private constructor() : BusinessException(AuthErrorCode.ALREADY_USED_NICKNAME) {

    companion object {
        @JvmField
        val EXCEPTION = UsedNicknameException()
    }

}