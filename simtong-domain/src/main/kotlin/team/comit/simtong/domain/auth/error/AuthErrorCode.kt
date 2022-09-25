package team.comit.simtong.domain.auth.error

import team.comit.simtong.global.error.ErrorProperty

/**
 *
 * Auth에 관련된 Error를 관리하는 AuthErrorCode
 *
 * @author Chokyunghyeon
 * @date 2022/09/09
 * @version 1.0.0
 **/
enum class AuthErrorCode(
    private val status: Int,
    private val message: String
): ErrorProperty {

    // 401
    AUTHCODE_NOT_FOUND(401, "인증 코드를 찾을 수 없음"),
    UNCERTIFIED_EMAIL(401, "인증되지 않은 이메일"),
    REFRESH_TOKEN_NOT_FOUND(401, "토큰을 찾을 수 없음"),

    // 409
    ALREADY_USED_EMAIL(409, "이미 사용된 이메일"),
    ALREADY_CERTIFIED_EMAIL(409, "이미 인증된 이메일"),

    // 429
    EXCEEDED_SEND_AUTHCODE_REQUEST(429, "인증 코드 발송 횟수 초과");

    override fun message(): String = message
    override fun status(): Int = status
}