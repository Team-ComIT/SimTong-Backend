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
    UNCERTIFIED_EMAIL(401, "인증되지 않은 이메일"),

    // 409
    ALREADY_USED_EMAIL(409, "이미 사용된 이메일");

    override fun message(): String = message
    override fun status(): Int = status
}