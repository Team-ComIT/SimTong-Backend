package team.comit.simtong.domain.user.usecase.dto

/**
 *
 * 회원 가입 정보를 전달하는 DomainSignUpRequest
 *
 * @author Chokyunghyeon
 * @date 2022/09/04
 * @version 1.0.0
 **/
data class DomainSignUpRequest(
    val name: String,

    val email: String,

    val password: String,

    val nickname: String,

    val employeeNumber: Int,

    val profileImagePath: String?
)