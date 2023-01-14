package team.comit.simtong.domain.user.dto.request

/**
 *
 * 사용자의 비밀번호 변경 요청 정보를 전달하는 ChangePasswordData
 *
 * @author Chokyunghyeon
 * @date 2022/10/14
 * @version 1.2.5
 **/
data class ChangePasswordData(
    val password: String,

    val newPassword: String
)