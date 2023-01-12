package team.comit.simtong.domain.user.dto.request

/**
 *
 * 회원 가입 정보를 전달하는 SignUpData
 *
 * @author Chokyunghyeon
 * @author kimbeomjin
 * @date 2022/09/04
 * @version 1.2.5
 **/
data class SignUpData(
    val name: String,

    val email: String,

    val password: String,

    val nickname: String,

    val employeeNumber: Int,

    val profileImagePath: String?,

    val deviceToken: String
)