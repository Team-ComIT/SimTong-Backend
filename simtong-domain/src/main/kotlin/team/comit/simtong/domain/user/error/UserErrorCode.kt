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
    DIFFERENT_PERMISSION_ACCOUNT(401, "다른 권한의 계정"),

    // 403
    NOT_ENOUGH_PERMISSION(403, "권한이 부족한 동작"),

    // 404
    USER_NOT_FOUND(404, "유저를 찾을 수 없음");

    override fun status(): Int = status

    override fun message(): String = message

}