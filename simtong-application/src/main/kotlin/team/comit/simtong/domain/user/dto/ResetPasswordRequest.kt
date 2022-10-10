package team.comit.simtong.domain.user.dto

/**
 *
 * 비밀번호 초기화을 하기 위한 ResetPasswordRequest
 *
 * @author Chokyunghyeon
 * @date 2022/09/27
 * @version 1.0.0
 **/
data class ResetPasswordRequest(
    val email: String,

    val employeeNumber: Int,

    val newPassword: String
)