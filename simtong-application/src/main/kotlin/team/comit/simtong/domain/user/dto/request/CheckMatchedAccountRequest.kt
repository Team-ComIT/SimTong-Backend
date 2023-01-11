package team.comit.simtong.domain.user.dto.request

/**
 *
 * 해당 사원번호와 이메일을 가진 계정 여부 확인을 요청하는 CheckMatchedAccountRequest
 *
 * @author Chokyunghyeon
 * @date 2022/10/15
 * @version 1.0.0
 **/
data class CheckMatchedAccountRequest(
    val employeeNumber: Int,
    val email: String
)