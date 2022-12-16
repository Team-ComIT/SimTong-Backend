package team.comit.simtong.domain.auth.exception

import team.comit.simtong.global.exception.BusinessException

/**
 *
 * Auth Domain에서 발생하는 Exception을 관리하는 AuthExceptions
 *
 * @author kimbeomjin
 * @date 2022/12/16
 * @version 1.0.0
 **/
sealed class AuthExceptions(
    override val status: Int,
    override val message: String
) : BusinessException(status, message) {

    // 401
    class DifferentAuthCode(message: String = DIFFERENT_AUTH_CODE) : AuthExceptions(401, message)
    class UncertifiedEmail(message: String = UNCERTIFIED_EMAIL) : AuthExceptions(401, message)
    class RequiredNewEmailAuthentication(message: String = REQUIRED_NEW_EMAIL_AUTHENTICATION) : AuthExceptions(401, message)

    // 404
    class RefreshTokenNotFound(message: String = REFRESH_TOKEN_NOT_FOUND) : AuthExceptions(404, message)

    // 409
    class AlreadyUsedEmail(message: String = ALREADY_USED_EMAIL) : AuthExceptions(409, message)
    class AlreadyCertifiedEmail(message: String = ALREADY_CERTIFIED_EMAIL) : AuthExceptions(409, message)

    // 429
    class ExceededSendAuthCodeRequest(message: String = EXCEEDED_SEND_AUTH_CODE_REQUEST) : AuthExceptions(429, message)

    companion object {
        private const val DIFFERENT_AUTH_CODE = "인증 코드가 일치하지 않습니다."
        private const val UNCERTIFIED_EMAIL = "인증되지 않은 이메일입니다."
        private const val REQUIRED_NEW_EMAIL_AUTHENTICATION = "새로운 이메일 인증이 필요합니다."
        private const val REFRESH_TOKEN_NOT_FOUND = "리프레시 토큰을 찾을 수 없습니다."
        private const val ALREADY_USED_EMAIL = "이미 사용중인 이메일입니다."
        private const val ALREADY_CERTIFIED_EMAIL = "이미 인증된 이메일입니다."
        private const val EXCEEDED_SEND_AUTH_CODE_REQUEST = "인증 코드 발송 횟수를 초과하였습니다."
    }
}
