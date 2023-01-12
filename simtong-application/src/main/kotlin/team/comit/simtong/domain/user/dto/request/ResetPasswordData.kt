package team.comit.simtong.domain.user.dto.request

/**
 *
 * 비밀번호 초기화 요청 정보를 전달하는 ResetPasswordData
 *
 * @author Chokyunghyeon
 * @date 2022/09/27
 * @version 1.2.5
 **/
data class ResetPasswordData(
    val email: String,

    val employeeNumber: Int,

    val newPassword: String
)