package team.comit.simtong.domain.user.error

import team.comit.simtong.global.error.ErrorProperty

/**
 *
 * User에 대해 발생하는 Error를 관리하는 UserErrorCode
 *
 * @author Chokyunghyeon
 * @author kimbeomjin
 * @date 2022/09/04
 * @version 1.0.0
 **/
enum class UserErrorCode(
    private val status: Int,
    private val message: String
): ErrorProperty {

    // 401
    DIFFERENT_PASSWORD(401, "비밀번호가 일치하지 않음"),

    // 404
    NOT_USER_ACCOUNT(404, "유저 계정이 아님"),
    NOT_ADMIN_ACCOUNT(404, "관리자 계정이 아님"),
    USER_NOT_FOUND(404, "유저를 찾을 수 없음");

    override fun status(): Int = status

    override fun message(): String = message

}