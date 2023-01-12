package team.comit.simtong.domain.user.dto.request

/**
 *
 * 해당 사원번호와 이메일을 가진 계정 여부 확인 요청 정보를 전달하는 CheckMatchedAccountData
 *
 * @author Chokyunghyeon
 * @date 2022/10/15
 * @version 1.2.5
 **/
data class CheckMatchedAccountData(
    val employeeNumber: Int,

    val email: String
)