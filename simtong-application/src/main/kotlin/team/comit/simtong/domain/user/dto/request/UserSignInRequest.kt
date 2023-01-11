package team.comit.simtong.domain.user.dto.request

/**
 *
 * 일반 사용자의 로그인을 정보를 전달하는 UserSignInRequest
 *
 * @author kimbeomjin
 * @date 2022/09/08
 * @version 1.0.0
 **/
data class UserSignInRequest(
    val employeeNumber: Int,

    val password: String,

    val deviceToken: String
)