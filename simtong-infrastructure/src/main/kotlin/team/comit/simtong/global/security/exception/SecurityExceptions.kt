package team.comit.simtong.global.security.exception

import team.comit.simtong.global.exception.BusinessException

/**
 *
 * Security에서 발생하는 Exception을 관리하는 SecurityExceptions
 *
 * @author kimbeomjin
 * @date 2022/12/17
 * @version 1.0.0
 **/
sealed class SecurityExceptions(
    override val status: Int,
    override val message: String
) : BusinessException(status, message) {

    // 401
    class ExpiredToken(message: String = EXPIRED_TOKEN) : SecurityExceptions(401, message)
    class UnexpectedToken(message: String = UNEXPECTED_TOKEN) : SecurityExceptions(401, message)
    class WrongTypeToken(message: String = WRONG_TYPE_TOKEN) : SecurityExceptions(401, message)
    class InvalidToken(message: String = INVALID_TOKEN) : SecurityExceptions(401, message)

    companion object {
        private const val EXPIRED_TOKEN = "토큰이 만료되었습니다."
        private const val UNEXPECTED_TOKEN = "알 수 없는 토큰입니다."
        private const val WRONG_TYPE_TOKEN = "토큰 유형이 적합하지 않습니다."
        private const val INVALID_TOKEN = "토큰이 유효하지 않습니다."
    }
}
