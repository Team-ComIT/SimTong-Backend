package team.comit.simtong.domain.user.exception

import team.comit.simtong.global.exception.BusinessException

/**
 *
 * User Domain에서 발생하는 Exception을 관리하는 UserExceptions
 *
 * @author kimbeomjin
 * @date 2022/12/16
 * @version 1.0.0
 **/
sealed class UserExceptions(
    override val status: Int,
    override val message: String
) : BusinessException(status, message) {

    // 401
    class DifferentPassword(message: String = DIFFERENT_PASSWORD) : UserExceptions(401, message)
    class DifferentPermissionAccount(message: String = DIFFERENT_PERMISSION_ACCOUNT) : UserExceptions(401, message)

    // 403
    class NotEnoughPermission(message: String = NOT_ENOUGH_PERMISSION) : UserExceptions(403, message)

    // 404
    class NotFound(message: String = NOT_FOUND) : UserExceptions(404, message)

    // 409
    class AlreadyUsedNickname(message: String = ALREADY_USED_NICKNAME) : UserExceptions(409, message)

    companion object {
        private const val DIFFERENT_PASSWORD = "비밀번호가 일치하지 않습니다."
        private const val DIFFERENT_PERMISSION_ACCOUNT = "다른 권한의 계정입니다."
        private const val NOT_ENOUGH_PERMISSION = "권한이 부족한 동작입니다."
        private const val NOT_FOUND = "사용자를 찾을 수 없습니다."
        private const val ALREADY_USED_NICKNAME = "이미 사용중인 닉네임입니다."
    }
}
