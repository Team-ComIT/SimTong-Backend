package team.comit.simtong.domain.user.dto

/**
 *
 * 해당 이름과 이메일을 가진 계정 여부 확인을 요청하는 CheckMatchedAccountRequest
 *
 * @author Chokyunghyeon
 * @date 2022/10/15
 * @version 1.0.0
 **/
data class CheckMatchedAccountRequest(
    val name: String,
    val email: String
)