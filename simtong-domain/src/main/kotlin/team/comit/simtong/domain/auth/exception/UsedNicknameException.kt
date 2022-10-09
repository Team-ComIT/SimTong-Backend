package team.comit.simtong.domain.auth.exception

import team.comit.simtong.domain.auth.error.AuthErrorCode
import team.comit.simtong.global.error.BusinessException

/**
 *
 * Already Used Nickname Error를 발생시키는 UsedNicknameException
 *
 * @author Chokyunghyeon
 * @date 2022/10/03
 * @version 1.0.0
 **/
class UsedNicknameException private constructor() : BusinessException(AuthErrorCode.ALREADY_USED_NICKNAME) {

    companion object {
        @JvmField
        val EXCEPTION = UsedNicknameException()
    }

}