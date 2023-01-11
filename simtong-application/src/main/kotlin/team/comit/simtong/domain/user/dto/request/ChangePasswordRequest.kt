package team.comit.simtong.domain.user.dto.request

/**
 *
 * 사용자의 비밀번호 변경 요청을 하는 ChangePasswordRequest
 *
 * @author Chokyunghyeon
 * @date 2022/10/14
 * @version 1.0.0
 **/
data class ChangePasswordRequest(
    val password: String,
    val newPassword: String
)