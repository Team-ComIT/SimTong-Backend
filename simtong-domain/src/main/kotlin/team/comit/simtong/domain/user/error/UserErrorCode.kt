package team.comit.simtong.domain.user.error

import team.comit.simtong.global.error.ErrorProperty

/**
 *
 *  User에 대해 발생하는 Error를 관리하는 UserErrorCode
 *
 * @author JoKyungHyeon
 * @date 2022/09/04
 * @version 1.0.0
 **/
enum class UserErrorCode(
    private val status: Int,
    private val message: String
): ErrorProperty {

    ALREADY_EMAIL_USED(409, "이미 사용된 이메일"),

    USER_NOT_FOUND(401, "유저를 찾을 수 없음"); // TODO Http status 사용에 대해 논의

    override fun status(): Int = status

    override fun message(): String = message

}