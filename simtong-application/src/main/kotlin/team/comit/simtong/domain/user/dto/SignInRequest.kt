package team.comit.simtong.domain.user.dto

/**
 *
 * 로그인을 정보를 전달하는 SignInRequest
 *
 * @author kimbeomjin
 * @date 2022/09/08
 * @version 1.0.0
 **/
data class SignInRequest(
    val employeeNumber: Int,

    val password: String
)