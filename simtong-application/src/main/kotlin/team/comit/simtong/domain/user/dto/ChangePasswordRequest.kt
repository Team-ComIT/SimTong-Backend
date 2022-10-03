package team.comit.simtong.domain.user.dto

/**
 *
 * 비밀번호 변경을 하기 위한  ChangePasswordRequest
 *
 * @author Chokyunghyeon
 * @date 2022/09/27
 * @version 1.0.0
 **/
data class ChangePasswordRequest(
    val email: String,

    val employeeNumber: Int,

    val newPassword: String
)