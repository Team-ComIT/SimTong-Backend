package team.comit.simtong.domain.user.dto.request

/**
 *
 * 관리자의 로그인 정보를 전달하는 AdminSignInData
 *
 * @author kimbeomjin
 * @author Chokyunghyeon
 * @date 2023/01/01
 * @version 1.2.5
 **/
data class AdminSignInData(
    val employeeNumber: Int,

    val password: String
)