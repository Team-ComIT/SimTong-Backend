package team.comit.simtong.domain.user.dto.request

/**
 *
 * 일반 사용자의 로그인 정보를 전달하는 UserSignInData
 *
 * @author kimbeomjin
 * @date 2022/09/08
 * @version 1.2.5
 **/
data class UserSignInData(
    val employeeNumber: Int,

    val password: String,

    val deviceToken: String
)