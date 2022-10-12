package team.comit.simtong.domain.user.exception

import team.comit.simtong.domain.user.error.UserErrorCode
import team.comit.simtong.global.error.BusinessException

/**
 *
 * 다른 권한의 계정 에러를 발생시키는 DifferentPermissionAccountException
 *
 * @author Chokyunghyeon
 * @date 2022/10/12
 * @version 1.0.0
 **/
class DifferentPermissionAccountException private constructor() : BusinessException(UserErrorCode.DIFFERENT_PERMISSION_ACCOUNT) {

    companion object {
        @JvmField
        val EXCEPTION = DifferentPermissionAccountException()
    }

}