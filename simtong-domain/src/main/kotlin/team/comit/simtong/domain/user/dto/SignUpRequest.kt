package team.comit.simtong.domain.user.dto

/**
 *
 * 회원 가입 정보를 전달하는 SignUpRequest
 *
 * @author Chokyunghyeon
 * @date 2022/09/04
 * @version 1.0.0
 **/
data class SignUpRequest(
    val name: String,

    val email: String,

    val password: String,

    val nickname: String?,

    val employeeNumber: Int,

    val profileImagePath: String?
)