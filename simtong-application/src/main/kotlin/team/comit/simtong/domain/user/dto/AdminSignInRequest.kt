package team.comit.simtong.domain.user.dto

/**
 *
 * 관리자의 로그인을 정보를 전달하는 AdminSignInRequest
 *
 * @author kimbeomjin
 * @date 2023/01/01
 * @version 1.1.0
 **/
data class AdminSignInRequest(
    val employeeNumber: Int,

    val password: String
)